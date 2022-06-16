package com.jflow.core.engine.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.factory.FlowInstanceFactory;
import com.jflow.core.domain.repository.FlowInstanceRepository;
import com.jflow.core.domain.repository.FlowSpecRepository;
import com.jflow.core.engine.ctx.Callback;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.ctx.Runtime;
import com.jflow.core.engine.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.flow.instance.node.StartNode;
import com.jflow.core.engine.service.Executor;
import com.jflow.core.engine.service.FlowInstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jflow.common.error.Errors.*;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlowInstanceServiceImpl implements FlowInstanceService {

    private final Executor executor;

    private final Runtime runtime;
    private final FlowSpecRepository flowSpecRepository;
    private final FlowInstanceFactory flowInstanceFactory;
    private final FlowInstanceRepository flowInstanceRepository;

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject args) {
        return start(flowSpecCode, args, null);
    }

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject args, String taskInstanceId) {
        FlowInstance flowInstance = createAndInit(flowSpecCode, args, taskInstanceId);
        log.info("create and start a new flow instance");
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, StartNode.NODE_ID);
        executor.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(runtime, flowInstance);
            nodeInstance.onSignal(context, null);
            flowInstanceRepository.save(flowInstance);
        });
        return flowInstance;
    }

    private FlowInstance createAndInit(String flowSpecCode, JSONObject args, String taskInstanceId) {
        Optional<FlowSpec> flowSpec = flowSpecRepository.getReleaseByCode(flowSpecCode);

        if (!flowSpec.isPresent()) {
            throw new FlowException(NO_RELEASED_FLOW_SPEC_VERSION_ERROR, flowSpecCode);
        }

        FlowSpec spec = flowSpec.get();
        int countOfRunning = flowInstanceRepository.getRunningCountOfSpecId(spec.getFlowSpecId());
        if (!spec.isEnableMultiInstance() && countOfRunning > 1) {
            throw new FlowException(UNSUPPORTED_MULTI_INSTANCE_ERROR, flowSpecCode);
        }

        FlowInstance flowInstance = flowInstanceFactory.create(spec, args, taskInstanceId);
        flowInstanceRepository.save(flowInstance);
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
        executor.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(runtime, flowInstance);
            nodeInstance.onFire(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }


    @Override
    public void retryNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        executor.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(runtime, flowInstance);
            nodeInstance.onRetry(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void skipNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        executor.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(runtime, flowInstance);
            nodeInstance.onSkip(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void cancelNode(String flowInstanceId, String nodeId, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstance, nodeId);
        executor.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(runtime, flowInstance);
            nodeInstance.onCancel(context, args);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void completeTask(String taskInstanceId, String status, JSONObject args) {
        FlowInstance flowInstance = flowInstanceRepository.getByTaskId(taskInstanceId);
        Optional<AbstractNodeInstance> node = flowInstance.findNodeByTaskId(taskInstanceId);
        if (!node.isPresent()) {
            throw new FlowException(NO_NODE_CONTAINS_TASK_ERROR, taskInstanceId);
        }
        executor.asyncRun(flowInstance.getFlowInstanceId(), () -> {
            Context context = Context.init(runtime, flowInstance);
            Callback callback = new Callback();
            callback.setTaskInstanceId(taskInstanceId);
            callback.setStatus(TaskInstanceStatusEnum.of(status));
            callback.setResult(args);
            node.get().onCallback(context, callback);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public void terminate(String flowInstanceId, JSONObject jsonObject) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        executor.asyncRun(flowInstanceId, () -> {
            Context context = Context.init(runtime, flowInstance);
            flowInstance.onTerminate(context);
            flowInstanceRepository.save(flowInstance);
        });
    }

    @Override
    public FlowInstance getById(String flowInstanceId) {
        return flowInstanceRepository.getById(flowInstanceId);
    }

}
