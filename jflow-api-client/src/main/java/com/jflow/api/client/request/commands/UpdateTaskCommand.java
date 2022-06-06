package com.jflow.api.client.request.commands;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class UpdateTaskCommand {
    private String taskInstanceId;
    private JSONObject context;
    private String taskStatus;
}
