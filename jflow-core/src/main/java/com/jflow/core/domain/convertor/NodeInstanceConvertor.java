package com.jflow.core.domain.convertor;

import com.jflow.core.domain.dto.NodeInstanceDTO;
import com.jflow.core.domain.dto.TaskInstanceDTO;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.flow.spec.NodeSpec;
import com.jflow.core.engine.flow.spec.TaskSpec;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class NodeInstanceConvertor {

    public NodeInstanceDTO convertNode(AbstractNodeInstance instance) {
        if (null == instance) {
            return null;
        }
        NodeInstanceDTO dto = new NodeInstanceDTO();
        dto.setNodeId(instance.getNodeId());
        dto.setNodeType(instance.getSpec().getNodeType());
        dto.setStatus(instance.getStatus());
        dto.setLatestTask(convertTask(instance.getLatestTask()));
        dto.setWaitAll(instance.getWaitAll());
        dto.setAutoFire(instance.getAutoFire());
        dto.setAutoSkip(instance.getAutoSkip());
        dto.setEnableSkip(instance.getEnableSkip());
        dto.setEnableRetry(instance.getEnableRetry());
        dto.setInterruptWhenSubmitFailed(instance.getInterruptWhenSubmitFailed());
        dto.setInterruptWhenExecuteFailed(instance.getInterruptWhenExecuteFailed());
        dto.setFirstSignalTime(instance.getFirstSignalTime());
        dto.setFireTime(instance.getFireTime());
        dto.setFinishTime(instance.getFinishTime());
        return dto;
    }

    public AbstractNodeInstance convertNode(NodeInstanceDTO dto, NodeSpec spec) {
        if (null == dto) {
            return null;
        }
        AbstractNodeInstance instance = dto.getNodeType().newNode();
        instance.setSpec(spec);
        instance.setStatus(dto.getStatus());
        instance.setLatestTask(convertTask(dto.getLatestTask(), spec.getTaskSpec()));
        instance.setIncoming(new HashSet<>());
        instance.setOutgoing(new HashSet<>());
        instance.setWaitAll(dto.getWaitAll());
        instance.setAutoFire(dto.getAutoFire());
        instance.setAutoSkip(dto.getAutoSkip());
        instance.setEnableSkip(dto.getEnableSkip());
        instance.setEnableRetry(dto.getEnableRetry());
        instance.setInterruptWhenSubmitFailed(dto.getInterruptWhenSubmitFailed());
        instance.setInterruptWhenExecuteFailed(dto.getInterruptWhenExecuteFailed());
        instance.setFirstSignalTime(dto.getFirstSignalTime());
        instance.setFireTime(dto.getFireTime());
        instance.setFinishTime(dto.getFinishTime());
        return instance;
    }

    public TaskInstanceDTO convertTask(TaskInstance instance) {
        if (null == instance) {
            return null;
        }
        TaskInstanceDTO dto = new TaskInstanceDTO();
        dto.setTaskInstanceId(instance.getTaskInstanceId());
        dto.setFlowInstanceId(instance.getFlowInstanceId());
        dto.setNodeId(instance.getNodeId());
        dto.setStatus(instance.getStatus());
        dto.setError(instance.getError());
        dto.setTaskContext(instance.getTaskContext());
        dto.setRecords(instance.getRecords());
        return dto;
    }

    public TaskInstance convertTask(TaskInstanceDTO dto, TaskSpec spec) {
        if (null == dto) {
            return null;
        }
        TaskInstance instance = new TaskInstance(spec);
        instance.setTaskInstanceId(dto.getTaskInstanceId());
        instance.setFlowInstanceId(dto.getFlowInstanceId());
        instance.setStatus(dto.getStatus());
        instance.setError(dto.getError());
        instance.setTaskContext(dto.getTaskContext());
        instance.setRecords(instance.getRecords());
        return instance;
    }

}
