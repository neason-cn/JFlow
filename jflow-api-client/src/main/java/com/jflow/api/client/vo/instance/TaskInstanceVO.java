package com.jflow.api.client.vo.instance;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskInstanceVO {
    private String taskInstanceId;
    private String flowInstanceId;
    private String nodeId;
    private String status;
    private String error;
    private JSONObject taskContext;
    private JSONObject records;
}
