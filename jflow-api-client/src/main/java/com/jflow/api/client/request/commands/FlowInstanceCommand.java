package com.jflow.api.client.request.commands;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * Operation for flow instance.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowInstanceCommand {
    private String flowInstanceId;
    private JSONObject context;
}
