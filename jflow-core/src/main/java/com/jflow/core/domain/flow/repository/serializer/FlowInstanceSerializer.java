package com.jflow.core.domain.flow.repository.serializer;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.domain.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowInstance;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import org.springframework.stereotype.Component;

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
        entity.setNodes(null);
        entity.setEdges(null);
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
        instance.setNodes(null);
        instance.setEdges(null);
        return instance;
    }
}
