package com.jflow.infra.impl.script;

import com.jflow.infra.spi.script.ScriptResult;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class DefaultScriptResult<T> implements ScriptResult<T> {

    private String error;
    private T result;

    public static <T> DefaultScriptResult<T> error(String error) {
        DefaultScriptResult<T> r = new DefaultScriptResult<>();
        r.setError(error);
        return r;
    }

    public static <T> DefaultScriptResult<T> success(T result) {
        DefaultScriptResult<T> r = new DefaultScriptResult<>();
        r.setResult(result);
        return r;
    }

}
