package com.jflow.app.endpoint.http.convertor;

import com.jflow.api.client.vo.instance.TaskInstanceVO;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.engine.flow.instance.TaskInstance;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class TaskVOConvertor {

    public Map<String, List<TaskInstanceVO>> groupByNodeId(List<TaskInstanceVO> tasks) {
        Map<String, List<TaskInstanceVO>> taskMap = new HashMap<>();
        if (CollectionUtils.isEmpty(tasks)) {
            return taskMap;
        }
        tasks.forEach(item -> {
            List<TaskInstanceVO> taskInstanceVOS = taskMap.computeIfAbsent(item.getNodeId(), k -> new ArrayList<>());
            taskInstanceVOS.add(item);
        });
        return taskMap;
    }

    public TaskInstanceVO convert(TaskInstance instance) {
        TaskInstanceVO vo = new TaskInstanceVO();
        vo.setTaskInstanceId(instance.getTaskInstanceId());
        vo.setFlowInstanceId(instance.getFlowInstanceId());
        vo.setNodeId(instance.getNodeId());
        vo.setStatus(instance.getStatus().getStatus());
        vo.setError(instance.getError());
        vo.setTaskContext(instance.getTaskContext());
        if (MapUtils.isNotEmpty(instance.getRecords())) {
            vo.setRecords(JsonUtil.toJson(instance.getRecords()));
        }
        return vo;
    }

}
