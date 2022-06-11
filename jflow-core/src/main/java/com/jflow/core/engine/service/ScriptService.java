package com.jflow.core.engine.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.ScriptContext;

/**
 * @author neason
 * @since 0.0.1
 */
public interface ScriptService {

    JSONObject replace(JSONObject template, ScriptContext context);

    Object parseString(String template, ScriptContext context);
}
