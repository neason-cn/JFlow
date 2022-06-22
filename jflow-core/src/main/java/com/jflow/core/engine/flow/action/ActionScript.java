package com.jflow.core.engine.flow.action;

import com.alibaba.fastjson2.TypeReference;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.infra.spi.script.type.Script;

/**
 * @author neason
 * @since 0.0.1
 */
public class ActionScript extends Script<ActionResponse> {

    public ActionScript(String content) {
        super(content);
    }

    @Override
    public TypeReference<ActionResponse> getResultType() {
        return new TypeReference<ActionResponse>() {
        };
    }
}
