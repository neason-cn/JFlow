package com.jflow.core.engine.flow.action;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.activity.ActionActivity;
import com.jflow.core.engine.flow.spec.ActionSpec;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractAction implements ActionActivity, Type {
    private transient ActionSpec actionSpec;

    @Override
    public String getType() {
        return this.actionSpec.getType();
    }

    public JSONObject toJson() {
        return JsonUtil.toJson(this);
    }

    protected ActionResponse resolveResult(Object result) {
        try {
            return JSONObject.parseObject(JsonUtil.toJsonString(result), ActionResponse.class);
        } catch (Exception e) {
            return ActionResponse.error("dubbo response parse error: ".concat(e.getMessage()));
        }
    }
}
