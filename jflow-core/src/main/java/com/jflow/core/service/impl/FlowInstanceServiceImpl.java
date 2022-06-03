package com.jflow.core.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.auth.FlowUser;
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
import com.jflow.core.service.FlowInstanceService;
import com.jflow.infra.spi.cache.CacheSpi;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.RequiredArgsConstructor;
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
public class FlowInstanceServiceImpl implements FlowInstanceService {

    private final FlowSpecRepository flowSpecRepository;
    private final FlowInstanceRepository flowInstanceRepository;
    private final FlowInstanceFactory flowInstanceFactory;
    private final ScriptSpi scriptSpi;
    private final CacheSpi cacheSpi;

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject args, FlowUser user) {
        FlowInstance flowInstance = createAndInit(flowSpecCode, args, user, null);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, StartNode.NODE_ID);
        Context context = Context.init(user, getRuntime(), flowInstance);
        nodeInstance.onFire(context, args);
        return flowInstance;
    }

    private FlowInstance createAndInit(String flowSpecCode, JSONObject args, FlowUser user, String taskInstanceId) {
        Optional<FlowSpec> flowSpec = flowSpecRepository.getReleaseByCode(flowSpecCode);

        if (!flowSpec.isPresent()) {
            throw new FlowException(NO_RELEASED_FLOW_SPEC_VERSION_ERROR, flowSpecCode);
        }

        FlowSpec spec = flowSpec.get();
        Optional<List<FlowInstance>> allInstanceOfSpec = flowInstanceRepository.getAllInstanceOfSpec(flowSpecCode);
        if (spec.isEnableMultiInstance() && allInstanceOfSpec.isPresent() && allInstanceOfSpec.get().size() > 1) {
            throw new FlowException(UNSUPPORTED_MULTI_INSTANCE_ERROR, flowSpecCode);
        }

        return flowInstanceFactory.create(spec, args, user, taskInstanceId);
    }

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject args, FlowUser user, String taskInstanceId) {
        FlowInstance flowInstance = createAndInit(flowSpecCode, args, user, taskInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, StartNode.NODE_ID);
        Context context = Context.init(user, getRuntime(), flowInstance);
        nodeInstance.onFire(context, args);
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
    public void fireNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        Context context = Context.init(user, getRuntime(), flowInstance);
        nodeInstance.onFire(context, args);
    }

    private Runtime getRuntime() {
        return Runtime.builder()
                .cacheSpi(cacheSpi)
                .scriptSpi(scriptSpi)
                .flowInstanceService(this)
                .build();
    }


    @Override
    public void retryNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        Context context = Context.init(user, getRuntime(), flowInstance);
        nodeInstance.onRetry(context, args);
    }

    @Override
    public void skipNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        Context context = Context.init(user, getRuntime(), flowInstance);
        nodeInstance.onSkip(context, args);
    }

    @Override
    public void cancelNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        Context context = Context.init(user, getRuntime(), flowInstance);
        nodeInstance.onCancel(context, args);
    }

    @Override
    public void completeTask(String taskInstanceId, JSONObject args, FlowUser user) {
        FlowInstance flowInstance = flowInstanceRepository.getByTaskId(taskInstanceId);
        Optional<AbstractNodeInstance> node = flowInstance.findNodeByTaskId(taskInstanceId);
        if (!node.isPresent()) {
            throw new FlowException(NO_NODE_CONTAINS_TASK_ERROR, taskInstanceId);
        }
        Context context = Context.init(user, getRuntime(), flowInstance);
        Callback callback = new Callback();
        node.get().onCallback(context, callback);
    }

    @Override
    public void terminate(String flowInstanceId, JSONObject jsonObject, FlowUser user) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        Context context = Context.init(user, getRuntime(), flowInstance);
        flowInstance.onTerminate(context);
    }

}
