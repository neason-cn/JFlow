package com.jflow.core.engine.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.RuntimeContext;

/**
 * @author neason
 * @since 0.0.1
 */
public interface ScriptService {

    JSONObject replace(JSONObject template, RuntimeContext context);

    Object parseString(String template, RuntimeContext context);
}
