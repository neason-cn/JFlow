package com.jflow.core.domain.dto;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.engine.flow.action.ActionRecord;
import lombok.Data;

import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskInstanceDTO {
    private String taskInstanceId;
    private String flowInstanceId;
    private String nodeId;
    private TaskInstanceStatusEnum status;
    private String error;
    private JSONObject taskContext;
    private Map<String, ActionRecord> records;
}
