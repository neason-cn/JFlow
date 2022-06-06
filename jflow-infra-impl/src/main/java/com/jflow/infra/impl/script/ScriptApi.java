package com.jflow.infra.impl.script;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.ScriptSpi;
import com.jflow.infra.spi.script.type.Script;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ScriptApi implements ScriptSpi {
    @Override
    public boolean validate(Script<?> script) {
        return false;
    }

    @Override
    public <T> ScriptResult<T> execute(Script<T> script, JSONObject context) {
        return null;
    }

    @Override
    public <T> ScriptResult<T> execute(String script, JSONObject context, TypeReference<T> type) {
        return null;
    }
}
