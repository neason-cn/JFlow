package com.jflow.infra.spi.script;

/**
 * The result after executing the script with some data.
 *
 * @param <T> the result type
 * @author neason
 * @since 0.0.1
 */
public class ScriptResult<T> {

    /**
     * some error when executing the script.
     */
    private String error;

    /**
     * the result after executing the script.
     */
    private T result;

}
