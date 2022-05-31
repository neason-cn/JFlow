package com.jflow.infra.spi.script;

import org.apache.commons.lang3.StringUtils;

/**
 * The result after executing the script with some data.
 *
 * @param <T> the result type
 * @author neason
 * @since 0.0.1
 */
public interface ScriptResult<T> {

    /**
     * @return execute script success of not.
     */
    default boolean hasError() {
        return StringUtils.isNoneBlank(getError());
    }

    /**
     * @return some error when executing the script.
     */
    String getError();

    /**
     * @return the result after executing the script.
     */
    T getResult();

}
