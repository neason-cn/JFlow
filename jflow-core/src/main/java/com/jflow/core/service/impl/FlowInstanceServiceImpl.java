package com.jflow.core.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.engine.Callback;
import com.jflow.core.domain.engine.Context;
import com.jflow.core.domain.engine.Runtime;
import com.jflow.core.domain.flow.aggregate.FlowInstance;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.factory.FlowInstanceFactory;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.flow.reference.instance.node.StartNode;
import com.jflow.core.domain.flow.repository.FlowInstanceRepository;
import com.jflow.core.domain.flow.repository.FlowSpecRepository;
import com.jflow.core.service.AsyncRunner;
import com.jflow.core.service.FlowInstanceService;
import com.jflow.infra.spi.cache.CacheSpi;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.jflow.common.error.Errors.*;

/**
 * @author neason
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class FlowInstanceServiceImpl implements FlowInstanceService, ApplicationContextAware {

    private final FlowSpecRepository flowSpecRepository;
    private final FlowInstanceRepository flowInstanceRepository;
    private final FlowInstanceFactory flowInstanceFactory;
    private final AsyncRunner asyncRunner;
    private final ScriptSpi scriptSpi;
    private final CacheSpi cacheSpi;
    private ApplicationContext applicationContext;

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject args) {
        return start(flowSpecCode, args, null);
    }

    private FlowInstance createAndInit(String flowSpecCode, JSONObject args, String taskInstanceId) {
        Optional<FlowSpec> flowSpec = flowSpecRepository.getReleaseByCode(flowSpecCode);

        if (!flowSpec.isPresent()) {
            throw new FlowException(NO_RELEASED_FLOW_SPEC_VERSION_ERROR, flowSpecCode);
        }

        FlowSpec spec = flowSpec.get();
        Optional<List<FlowInstance>> allInstanceOfSpec = flowInstanceRepository.getAllInstanceOfSpec(flowSpecCode);
        if (spec.isEnableMultiInstance() && allInstanceOfSpec.isPresent() && allInstanceOfSpec.get().size() > 1) {
            throw new FlowException(UNSUPPORTED_MULTI_INSTANCE_ERROR, flowSpecCode);
        }

        FlowInstance flowInstance = flowInstanceFactory.create(spec, args, taskInstanceId);
        flowInstanceRepository.save(flowInstance);
        return flowInstance;
    }

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject args, String taskInstanceId) {
        FlowInstance flowInstance = createAndInit(flowSpecCode, args, taskInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, StartNode.NODE_ID);
        asyncRunner.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            nodeInstance.onFire(context, args);
            flowInstanceRepository.save(flowInstance);
        });
        return flowInstance;
    }

    AbstractNodeInstance getNodeOfFlow(FlowInstance flowInstance, String nodeId) {
        Optional<AbstractNodeInstance> node = flowInstance.findNode(nodeId);
        if (!node.isPresent()) {
            throw new FlowException(NO_SUCH_NODE_IN_FLOW, nodeId, flowInstance.getFlowInstanceId());
        }
        return node.get();
    }

    @Override
    public void fireNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        asyncRunner.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            nodeInstance.onFire(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    private Runtime getRuntime() {
        return Runtime.builder()
                .cacheSpi(cacheSpi)
                .scriptSpi(scriptSpi)
                .flowInstanceService(this)
                .applicationContext(applicationContext)
                .build();
    }


    @Override
    public void retryNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        asyncRunner.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            nodeInstance.onRetry(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void skipNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        asyncRunner.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            nodeInstance.onSkip(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void cancelNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        asyncRunner.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            nodeInstance.onCancel(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void completeTask(String taskInstanceId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getByTaskId(taskInstanceId);
        Optional<AbstractNodeInstance> node = flowInstance.findNodeByTaskId(taskInstanceId);
        if (!node.isPresent()) {
            throw new FlowException(NO_NODE_CONTAINS_TASK_ERROR, taskInstanceId);
        }
        asyncRunner.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            Callback callback = new Callback();
            node.get().onCallback(context, callback);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void terminate(String flowInstanceId, JSONObject jsonObject) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        asyncRunner.asyncRun(flowInstanceId, () -> {
            Context context = Context.init(getRuntime(), flowInstance);
            flowInstance.onTerminate(context);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
