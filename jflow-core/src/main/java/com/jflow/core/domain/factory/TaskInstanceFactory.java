package com.jflow.core.domain.factory;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.TaskSpec;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@Component
public class TaskInstanceFactory {

    public TaskInstance create(TaskSpec spec, String flowInstanceId, String nodeId, JSONObject context) {
        TaskInstance instance = new TaskInstance(spec);
        instance.setTaskInstanceId(IdUtil.fastSimpleUUID());
        instance.setFlowInstanceId(flowInstanceId);
        instance.setNodeId(nodeId);
        if (MapUtils.isEmpty(context)) {
            context = new JSONObject();
        }
        context.put(TaskInstance.TASK_INSTANCE_ID, instance.getTaskInstanceId());
        instance.setTaskContext(context);
        instance.setStatus(TaskInstanceStatusEnum.INIT);
        instance.setRecords(new HashMap<>());
        return instance;
    }

}
