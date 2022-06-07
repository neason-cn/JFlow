package com.jflow.core.domain.flow.repository.serializer;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.domain.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.domain.flow.reference.action.ActionRecord;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class TaskInstanceSerializer {

    public TaskInstanceEntity serialize(TaskInstance domain) {
        TaskInstanceEntity entity = new TaskInstanceEntity();
        entity.setTaskInstanceId(domain.getTaskInstanceId());
        entity.setFlowInstanceId(domain.getFlowInstanceId());
        entity.setNodeId(domain.getNodeId());
        entity.setStatus(domain.getStatus().getStatus());
        entity.setType(domain.getType());
        entity.setTaskContext(JsonUtil.toJsonString(domain.getTaskContext()));
        entity.setError(domain.getError());
        entity.setRecords(JsonUtil.toJsonString(domain.getRecords()));
        return entity;
    }

    public TaskInstance deSerialize(TaskInstanceEntity entity, TaskSpec spec) {
        TaskInstance instance = new TaskInstance();
        instance.setTaskInstanceId(entity.getTaskInstanceId());
        instance.setFlowInstanceId(entity.getFlowInstanceId());
        instance.setNodeId(entity.getNodeId());
        instance.setTaskSpec(spec);
        instance.setStatus(TaskInstanceStatusEnum.of(entity.getStatus()));
        instance.setError(entity.getError());
        instance.setTaskContext(JSONObject.parseObject(entity.getTaskContext()));
        instance.setRecords(new TypeReference<Map<String, ActionRecord>>() {
        }.parseObject(entity.getRecords()));
        return instance;
    }
}
