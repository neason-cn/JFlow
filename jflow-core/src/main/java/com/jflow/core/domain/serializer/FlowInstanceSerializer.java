package com.jflow.core.domain.serializer;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.common.exception.FlowException;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.domain.convertor.EdgeInstanceConvertor;
import com.jflow.core.domain.convertor.NodeInstanceConvertor;
import com.jflow.core.domain.dto.EdgeInstanceDTO;
import com.jflow.core.domain.dto.NodeInstanceDTO;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.flow.spec.EdgeSpec;
import com.jflow.core.engine.flow.spec.NodeSpec;
import com.jflow.core.engine.graph.Graph;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jflow.common.error.Errors.NO_SUCH_EDGE_IN_FLOW;
import static com.jflow.common.error.Errors.NO_SUCH_NODE_IN_FLOW;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowInstanceSerializer {

    private final NodeInstanceConvertor nodeInstanceConvertor;
    private final EdgeInstanceConvertor edgeInstanceConvertor;

    public FlowInstanceEntity serialize(FlowInstance domain) {
        FlowInstanceEntity entity = new FlowInstanceEntity();
        entity.setFlowInstanceId(domain.getFlowInstanceId());
        entity.setFlowSpecId(domain.getSpec().getFlowSpecId());
        entity.setTaskInstanceId(domain.getParentTaskInstanceId());
        entity.setStatus(domain.getStatus().getStatus());
        entity.setContext(JsonUtil.toJsonString(domain.getContext()));
        entity.setInput(JsonUtil.toJsonString(domain.getInput()));
        entity.setOutput(JsonUtil.toJsonString(domain.getOutput()));
        entity.setCreateAt(domain.getCreateAt());
        entity.setCancelAt(domain.getTerminateAt());

        Set<NodeInstanceDTO> nodes = domain.getNodes().stream()
                .map(nodeInstanceConvertor::convertNode)
                .collect(Collectors.toSet());
        entity.setNodes(JsonUtil.toJsonString(nodes));

        Set<EdgeInstanceDTO> edges = domain.getEdges().stream()
                .map(edgeInstanceConvertor::convert)
                .collect(Collectors.toSet());
        entity.setEdges(JsonUtil.toJsonString(edges));
        return entity;
    }


    public FlowInstance deSerialize(FlowInstanceEntity entity, FlowSpec spec) {
        FlowInstance instance = new FlowInstance(spec);
        instance.setFlowInstanceId(entity.getFlowInstanceId());
        instance.setParentTaskInstanceId(entity.getTaskInstanceId());
        instance.setStatus(FlowInstanceStatusEnum.of(entity.getStatus()));
        instance.setInput(JSONObject.parseObject(entity.getInput()));
        instance.setOutput(JSONObject.parseObject(entity.getOutput()));
        instance.setContext(JSONObject.parseObject(entity.getContext()));
        instance.setCreateAt(entity.getCreateAt());
        instance.setTerminateAt(entity.getCancelAt());

        Set<NodeInstanceDTO> nodeDTOs = new TypeReference<Set<NodeInstanceDTO>>() {
        }.parseObject(entity.getNodes());
        Set<AbstractNodeInstance> nodes = nodeDTOs.stream()
                .map(dto -> {
                    Optional<NodeSpec> nodeSpec = spec.findNode(dto.getNodeId());
                    if (nodeSpec.isPresent()) {
                        return nodeInstanceConvertor.convertNode(dto, nodeSpec.get());
                    }
                    throw new FlowException(NO_SUCH_NODE_IN_FLOW, dto.getNodeId());
                })
                .collect(Collectors.toSet());


        Set<EdgeInstanceDTO> edgeDTOs = new TypeReference<Set<EdgeInstanceDTO>>() {
        }.parseObject(entity.getEdges());
        Set<EdgeInstance> edges = edgeDTOs.stream()
                .map(dto -> {
                    Optional<EdgeSpec> edgeSpec = spec.findEdge(dto.getEdgeId());
                    if (edgeSpec.isPresent()) {
                        return edgeInstanceConvertor.convert(dto, edgeSpec.get());
                    }
                    throw new FlowException(NO_SUCH_EDGE_IN_FLOW, dto.getEdgeId());
                }).collect(Collectors.toSet());


        Graph.connect(nodes, edges);

        instance.setNodes(nodes);
        instance.setEdges(edges);
        return instance;
    }
}
