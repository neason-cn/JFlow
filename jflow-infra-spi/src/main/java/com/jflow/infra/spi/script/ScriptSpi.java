package com.jflow.infra.spi.script;

import com.alibaba.fastjson2.JSONObject;

/**
 * The SPI of script abilities.
 *
 * @param <S> the script impl type
 * @author neason
 * @since 0.0.1
 */
public interface ScriptSpi<S extends Script> {

    /**
     * Validate the script content.
     *
     * @param script the script
     * @return the script content is legal or not.
     */
    boolean validate(S script);

    /**
     * Execute a script.
     *
     * @param script  the script
     * @param context the data
     * @param type    class of the result type
     * @param <T>     the result type
     * @return the result wrapper
     */
    <T> ScriptResult<T> execute(S script, JSONObject context, Class<T> type);

}
