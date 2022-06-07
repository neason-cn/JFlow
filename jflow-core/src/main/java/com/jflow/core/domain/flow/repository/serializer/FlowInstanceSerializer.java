package com.jflow.core.domain.flow.repository.serializer;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.domain.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowInstance;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.reference.instance.EdgeInstance;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.graph.Graph;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowInstanceSerializer {

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
        entity.setCancelAt(domain.getCancelAt());
        entity.setNodes(JsonUtil.toJsonString(domain.getNodes(), JSONWriter.Feature.WriteClassName));
        entity.setEdges(JsonUtil.toJsonString(domain.getEdges(), JSONWriter.Feature.WriteClassName));
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
        instance.setCancelAt(entity.getCancelAt());

        Set<AbstractNodeInstance> nodes = new TypeReference<Set<AbstractNodeInstance>>() {
        }.parseObject(entity.getNodes());
        Set<EdgeInstance> edges = new TypeReference<Set<EdgeInstance>>() {
        }.parseObject(entity.getEdges());
        Graph.connect(nodes, edges);

        instance.setNodes(nodes);
        instance.setEdges(edges);
        return instance;
    }
}
