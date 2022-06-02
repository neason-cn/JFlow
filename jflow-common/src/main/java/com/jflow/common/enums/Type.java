package com.jflow.common.enums;

/**
 * @author neason
 * @since 0.0.1
 */
public interface Type {

    String KEY_IN_JSON = "type";

    /**
     * The symbol which use to distinguish the spec or instance.
     *
     * @return type
     */
    String getType();

}
