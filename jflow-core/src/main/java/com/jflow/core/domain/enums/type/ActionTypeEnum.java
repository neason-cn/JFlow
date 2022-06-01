package com.jflow.core.domain.enums.type;

import com.jflow.common.enums.Type;
import lombok.Getter;

/**
 * The enum of action type.
 *
 * @author neason
 * @since 0.0.1
 */
public enum ActionTypeEnum implements Type {

    /**
     * Rpc invoke, such as Dubbo, gRpc.
     */
    RPC("RPC"),

    /**
     * Http/Https invoke.
     */
    HTTP("HTTP"),

    /**
     * Do nothing.
     */
    EMPTY("EMPTY"),

    /**
     * Execute a script.
     */
    SCRIPT("SCRIPT"),

    /**
     * Send a message.
     */
    MESSAGE("MESSAGE");

    @Getter
    private final String type;

    ActionTypeEnum(String type) {
        this.type = type;
    }

    public static ActionTypeEnum of(String type) {
        for (ActionTypeEnum value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(ActionTypeEnum.class, type);
    }

}
