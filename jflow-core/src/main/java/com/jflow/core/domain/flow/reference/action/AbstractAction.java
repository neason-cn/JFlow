package com.jflow.core.domain.flow.reference.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.domain.engine.ActionResponse;
import com.jflow.core.domain.engine.activity.ActionActivity;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
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
            return JSONObject.parseObject(JSON.toJSONString(result), ActionResponse.class);
        } catch (Exception e) {
            return ActionResponse.error("dubbo response parse error: ".concat(e.getMessage()));
        }
    }
}
