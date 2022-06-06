package com.jflow.core.domain.flow.reference.action;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.engine.ActionResponse;
import com.jflow.core.domain.enums.type.ActionTypeEnum;
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
