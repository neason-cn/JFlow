package com.jflow.core.domain.serializer;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.engine.enums.status.FlowSpecStatusEnum;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.EdgeSpec;
import com.jflow.core.engine.flow.spec.NodeSpec;
import com.jflow.core.engine.graph.Graph;
import com.jflow.infra.spi.script.type.JsonScript;
import com.jflow.infra.spi.storage.entity.FlowSpecEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowSpecSerializer {

    public FlowSpecEntity serialize(FlowSpec domain) {
        FlowSpecEntity entity = new FlowSpecEntity();
        entity.setSpecId(domain.getFlowSpecId());
        entity.setSpecCode(domain.getFlowSpecCode());
        entity.setSpecVersion(domain.getFlowSpecVersion());
        entity.setSpecDesc(domain.getDescription());
        entity.setSpecStatus(domain.getStatus().getStatus());
        entity.setEnableMultiInstance(domain.isEnableMultiInstance());
        entity.setInitContext(JsonUtil.toJsonString(domain.getInitContext()));
        entity.setOutputScript(domain.getOutputScript().getContent());
        entity.setScheduled(domain.isScheduled());
        entity.setCron(domain.getCron());
        entity.setStartAction(JsonUtil.toJsonString(domain.getOnStart()));
        entity.setEndAction(JsonUtil.toJsonString(domain.getOnEnd()));
        entity.setCreateAt(domain.getCreateAt());
        entity.setReleaseAt(domain.getReleaseAt());

        entity.setNodes(JsonUtil.toJsonString(domain.getNodes()));
        entity.setEdges(JsonUtil.toJsonString(domain.getEdges()));
        return entity;
    }


    public FlowSpec deSerialize(FlowSpecEntity entity) {
        FlowSpec spec = new FlowSpec();
        spec.setFlowSpecId(entity.getSpecId());
        spec.setFlowSpecCode(entity.getSpecCode());
        spec.setFlowSpecVersion(entity.getSpecVersion());
        spec.setDescription(entity.getSpecDesc());
        spec.setStatus(FlowSpecStatusEnum.of(entity.getSpecStatus()));
        spec.setEnableMultiInstance(entity.isEnableMultiInstance());
        spec.setInitContext(JSONObject.parseObject(entity.getInitContext()));
        spec.setOutputScript(new JsonScript(entity.getOutputScript()));
        spec.setScheduled(entity.isScheduled());
        spec.setCron(entity.getCron());
        spec.setOnStart(new TypeReference<ActionSpec>() {
        }.parseObject(entity.getStartAction()));
        spec.setOnEnd(new TypeReference<ActionSpec>() {
        }.parseObject(entity.getEndAction()));
        spec.setCreateAt(entity.getCreateAt());
        spec.setReleaseAt(entity.getReleaseAt());

        Set<NodeSpec> nodes = new TypeReference<Set<NodeSpec>>() {
        }.parseObject(entity.getNodes());
        Set<EdgeSpec> edges = new TypeReference<Set<EdgeSpec>>() {
        }.parseObject(entity.getEdges());
        Graph.connect(nodes, edges);

        spec.setNodes(nodes);
        spec.setEdges(edges);
        return spec;
    }

}
