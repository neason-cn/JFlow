package com.jflow.core.domain.flow.repository.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.core.domain.enums.status.FlowSpecStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import com.jflow.infra.spi.script.type.JsonScript;
import com.jflow.infra.spi.storage.entity.FlowSpecEntity;
import org.springframework.stereotype.Component;

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
        entity.setInitContext(domain.getInitContext().toJSONString());
        entity.setOutputScript(domain.getOutputScript().getContent());
        entity.setScheduled(domain.isScheduled());
        entity.setCron(domain.getCron());
        entity.setStartAction(JSON.toJSONString(domain.getOnStart()));
        entity.setEndAction(JSON.toJSONString(domain.getOnEnd()));
        entity.setCreateAt(domain.getCreateAt());
        entity.setReleaseAt(domain.getReleaseAt());
        // todo
        entity.setNodes(null);
        entity.setEdges(null);
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
        // todo
        spec.setNodes(null);
        spec.setEdges(null);
        return spec;
    }

}
