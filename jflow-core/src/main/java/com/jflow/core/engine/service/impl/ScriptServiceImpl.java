package com.jflow.core.engine.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.ScriptContext;
import com.jflow.core.engine.service.ScriptService;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private static final String FLOW_CONTEXT_KEY = "flowCtx";
    private static final String TASK_CONTEXT_KEY = "taskCtx";
    private static final String SCRIPT_PREFIX = "#!script";
    private final ScriptSpi scriptSpi;

    @Override
    public JSONObject replace(JSONObject template, ScriptContext context) {
        if (null == template) {
            return new JSONObject();
        }

        final ScriptContext scriptContext = new ScriptContext(
                context == null ? new JSONObject() : context.getFlowContext(),
                context == null ? new JSONObject() : context.getTaskContext());

        JSONObject result = new JSONObject();
        template.forEach((k, v) -> {
            result.put(k, parseObject(v, scriptContext));
        });
        return result;
    }

    @Override
    public Object parseString(String template, ScriptContext context) {
        if (null == template) {
            return null;
        }

        // not script
        if (!matchScript(template)) {
            return template;
        }

        final ScriptContext scriptContext = new ScriptContext(
                context == null ? new JSONObject() : context.getFlowContext(),
                context == null ? new JSONObject() : context.getTaskContext());

        String script = removeScriptPrefix(template);

        ScriptResult<Object> scriptResult = scriptSpi.execute(script, buildContextForScriptSpi(scriptContext));
        if (null == scriptResult || scriptResult.hasError()) {
            return null;
        }
        return scriptResult.getResult();
    }

    /**
     * The string is a script expression or not( start with '#!script' ).
     */
    private boolean matchScript(String template) {
        if (StringUtils.isBlank(template)) {
            return false;
        }
        return template.startsWith(SCRIPT_PREFIX);
    }

    /**
     * Remove the script prefix and return the script content.
     */
    private String removeScriptPrefix(String template) {
        if (StringUtils.isBlank(template) || template.length() < SCRIPT_PREFIX.length()) {
            return template;
        }
        return template.substring(SCRIPT_PREFIX.length()).trim();
    }

    /**
     * Wrap RuntimeContext to JSONObject context which used is ScriptSpi.
     */
    private JSONObject buildContextForScriptSpi(ScriptContext context) {
        JSONObject json = new JSONObject();
        json.put(FLOW_CONTEXT_KEY, context.getFlowContext());
        json.put(TASK_CONTEXT_KEY, context.getTaskContext());
        return json;
    }

    /**
     * Resolve all fields of a Object.
     */
    private Object parseObject(Object template, ScriptContext context) {
        if (null == template) {
            return null;
        }

        if (template instanceof String) {
            return parseString(template.toString(), context);
        }

        if (template instanceof Collection) {
            JSONArray array = new JSONArray();
            Collection<?> collections = (Collection<?>) template;
            collections.forEach(item -> {
                array.add(parseObject(item, context));
            });
            return array;
        }

        if (template instanceof Map) {
            JSONObject json = new JSONObject();
            Map<?, ?> map = (Map<?, ?>) template;
            map.forEach((k, v) -> {
                if (null != k) {
                    json.put(k.toString(), parseObject(v, context));
                }
            });
            return json;
        }

        return template;
    }

}
