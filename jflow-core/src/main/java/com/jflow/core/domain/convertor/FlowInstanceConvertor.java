package com.jflow.core.domain.convertor;

import com.jflow.api.client.vo.instance.EdgeInstanceVO;
import com.jflow.api.client.vo.instance.FlowInstanceVO;
import com.jflow.api.client.vo.instance.NodeInstanceVO;
import com.jflow.api.client.vo.instance.TaskInstanceVO;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowInstanceConvertor {


    public FlowInstanceVO convert(FlowInstance instance) {
        FlowInstanceVO vo = new FlowInstanceVO();
        vo.setFlowInstanceId(instance.getFlowInstanceId());
        vo.setParentTaskInstanceId(instance.getParentTaskInstanceId());
        vo.setStatus(instance.getStatus().getStatus());
        vo.setInput(instance.getInput());
        vo.setOutput(instance.getOutput());
        vo.setContext(instance.getContext());
        Set<NodeInstanceVO> nodes = instance.getNodes().stream()
                .map(this::convertNode)
                .collect(Collectors.toSet());
        vo.setNodes(nodes);
        Set<EdgeInstanceVO> edges = instance.getEdges().stream()
                .map(this::convertEdge)
                .collect(Collectors.toSet());
        vo.setEdges(edges);
        vo.setCreateAt(instance.getCreateAt());
        vo.setCancelAt(vo.getCancelAt());
        return vo;
    }

    private NodeInstanceVO convertNode(AbstractNodeInstance instance) {
        if (null == instance) {
            return null;
        }
        NodeInstanceVO vo = new NodeInstanceVO();
        vo.setNodeId(instance.getNodeId());
        vo.setNodeType(instance.getSpec().getNodeType().getType());
        vo.setStatus(instance.getStatus().getStatus());
        vo.setLatestTask(convertTask(instance.getLatestTask()));
        vo.setWaitAll(instance.getWaitAll());
        vo.setAutoFire(instance.getAutoFire());
        vo.setAutoSkip(instance.getAutoSkip());
        vo.setEnableSkip(instance.getEnableSkip());
        vo.setEnableRetry(instance.getEnableRetry());
        vo.setInterruptWhenSubmitFailed(instance.getInterruptWhenSubmitFailed());
        vo.setInterruptWhenExecuteFailed(instance.getInterruptWhenExecuteFailed());
        vo.setFirstSignalTime(instance.getFirstSignalTime());
        vo.setFireTime(instance.getFireTime());
        vo.setFinishTime(instance.getFinishTime());
        return vo;
    }

    private EdgeInstanceVO convertEdge(EdgeInstance instance) {
        if (null == instance) {
            return null;
        }
        EdgeInstanceVO vo = new EdgeInstanceVO();
        vo.setEdgeId(instance.getEdgeId());
        vo.setStatus(instance.getStatus().getStatus());
        vo.setError(instance.getError());
        vo.setSourceNodeId(instance.getSourceNodeId());
        vo.setTargetNodeId(instance.getTargetNodeId());
        return vo;
    }

    private TaskInstanceVO convertTask(TaskInstance instance) {
        if (null == instance) {
            return null;
        }
        TaskInstanceVO vo = new TaskInstanceVO();
        vo.setTaskInstanceId(instance.getTaskInstanceId());
        vo.setFlowInstanceId(instance.getFlowInstanceId());
        vo.setNodeId(instance.getNodeId());
        vo.setStatus(instance.getStatus().getStatus());
        vo.setError(instance.getError());
        vo.setTaskContext(instance.getTaskContext());
        if (null != instance.getRecords()) {
            vo.setRecords(JsonUtil.toJson(instance.getRecords()));
        }
        return vo;
    }

}
