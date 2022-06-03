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
public class StartFlowInstanceCommand extends AuthCommand {
    private String flowSpecCode;
    private JSONObject context;
}
