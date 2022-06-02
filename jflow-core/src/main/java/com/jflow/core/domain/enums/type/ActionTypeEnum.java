package com.jflow.core.domain.enums.type;

import com.jflow.common.enums.Type;
import com.jflow.core.domain.flow.reference.spec.action.AbstractActionSpec;
import com.jflow.core.domain.flow.reference.spec.action.HttpActionSpec;
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
    RPC("RPC", null),

    /**
     * Http/Https invoke.
     */
    HTTP("HTTP", HttpActionSpec.class),

    /**
     * Do nothing.
     */
    EMPTY("EMPTY", null),

    /**
     * Execute a script.
     */
    SCRIPT("SCRIPT", null),

    /**
     * Send a message.
     */
    MESSAGE("MESSAGE", null);

    @Getter
    private final String type;
    @Getter
    private final Class<? extends AbstractActionSpec> clazz;

    ActionTypeEnum(String type, Class<? extends AbstractActionSpec> clazz) {
        this.type = type;
        this.clazz = clazz;
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
