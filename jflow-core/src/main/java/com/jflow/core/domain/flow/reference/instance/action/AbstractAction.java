package com.jflow.core.domain.flow.reference.instance.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.core.domain.engine.ActionResult;
import com.jflow.core.domain.engine.activity.ActionActivity;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractAction implements ActionActivity, Type {
    private ActionSpec actionSpec;

    @Override
    public String getType() {
        return this.actionSpec.getType();
    }

    protected ActionResult resolveResult(Object result) {
        try {
            return JSONObject.parseObject(JSON.toJSONString(result), ActionResult.class);
        } catch (Exception e) {
            return ActionResult.error("dubbo response parse error: ".concat(e.getMessage()));
        }
    }
}
