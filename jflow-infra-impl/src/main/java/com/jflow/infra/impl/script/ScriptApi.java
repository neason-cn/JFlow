package com.jflow.infra.impl.script;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.ScriptSpi;
import com.jflow.infra.spi.script.type.Script;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.config.QLExpressRunStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ScriptApi implements ScriptSpi {

    private static final String ERROR_JOIN = "; ";
    private final ExpressRunner runner = new ExpressRunner(false, false);

    @PostConstruct
    private void init() {
        QLExpressRunStrategy.setForbidInvokeSecurityRiskMethods(true);
    }

    @Override
    public boolean validate(Script<?> script) {
        if (null == script || StringUtils.isBlank(script.getContent())) {
            return false;
        }

        return StringUtils.isNoneBlank(validate(script.getContent()));
    }

    private String validate(String script) {
        try {
            runner.parseInstructionSet(script);
            return null;
        } catch (Exception e) {
            return "error: ".concat(e.getMessage());
        }
    }

    @Override
    public <T> ScriptResult<T> execute(Script<T> script, JSONObject context) {
        if (null == script) {
            return DefaultScriptResult.error("the script is null");
        }
        return execute(script.getContent(), context, script.getResultType());
    }

    private IExpressContext<String, Object> convert(JSONObject context) {
        DefaultContext<String, Object> ctx = new DefaultContext<>();
        ctx.putAll(context);
        return ctx;
    }

    @Override
    public <T> ScriptResult<T> execute(String script, JSONObject context, TypeReference<T> type) {
        ScriptResult<Object> execute = execute(script, context);
        DefaultScriptResult<T> copy = new DefaultScriptResult<>();
        if (null != execute && null != execute.getResult()) {
            copy.setResult(type.parseObject(JSON.toJSONString(execute.getResult())));
            copy.setError(execute.getError());
            return copy;
        }
        return DefaultScriptResult.error("null");
    }

    @Override
    public ScriptResult<Object> execute(String script, JSONObject context) {
        if (null == script) {
            return DefaultScriptResult.error("the script is null");
        }

        String message = validate(script);
        if (StringUtils.isNotBlank(message)) {
            return DefaultScriptResult.error(message);
        }

        DefaultScriptResult<Object> result = new DefaultScriptResult<>();
        List<String> errors = new ArrayList<>();
        try {
            Object execute = runner.execute(script, convert(context), errors, true, false);
            if (CollectionUtils.isEmpty(errors)) {
                result.setResult(execute);
            } else {
                result.setError(String.join(ERROR_JOIN, errors));
            }
        } catch (Exception e) {
            result.setError(String.join(ERROR_JOIN, errors));
        }

        return result;
    }
}
