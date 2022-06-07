package com.jflow.core.engine.flow.action;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.enums.type.ActionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
public class ActionRecord {
    private ActionTypeEnum type;
    private JSONObject request;
    private ActionResponse response;
}
