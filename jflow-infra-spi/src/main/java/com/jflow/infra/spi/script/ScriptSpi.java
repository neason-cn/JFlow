package com.jflow.infra.spi.script;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.infra.spi.script.type.Script;

/**
 * The SPI of script abilities.
 *
 * @author neason
 * @since 0.0.1
 */
public interface ScriptSpi {

    /**
     * Validate the script content.
     *
     * @param script the script
     * @return the script content is legal or not.
     */
    boolean validate(Script<?> script);

    /**
     * Execute a script.
     *
     * @param script  the script
     * @param context the data
     * @param <T>     the result type
     * @return the result wrapper
     */
    <T> ScriptResult<T> execute(Script<T> script, JSONObject context);

}
