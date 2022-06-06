package com.jflow.api.client.request.commands;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class StartFlowInstanceCommand {
    private String flowSpecCode;
    private JSONObject context;
}
