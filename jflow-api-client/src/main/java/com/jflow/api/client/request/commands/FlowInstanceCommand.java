package com.jflow.api.client.request.commands;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Operation for flow instance.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowInstanceCommand extends AuthCommand {
    private String flowInstanceId;
    private JSONObject context;
}
