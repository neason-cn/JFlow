package com.jflow.api.client.request.commands;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskCommand extends AuthCommand {
    private String taskInstanceId;
    private JSONObject context;
    private String taskStatus;
}
