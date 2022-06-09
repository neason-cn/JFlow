package com.jflow.core.engine.enums.type;

import com.jflow.common.enums.Type;
import com.jflow.core.engine.flow.action.*;
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
    DUBBO("DUBBO", DubboAction.class),

    /**
     * Http/Https invoke.
     */
    HTTP("HTTP", HttpAction.class),

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
    MESSAGE("MESSAGE", KafkaAction.class),

    BEAN("BEAN", BeanAction.class),

    SUB_FLOW("SUB_FLOW", SubFlowAction.class);

    @Getter
    private final String type;
    @Getter
    private final Class<? extends AbstractAction> clazz;

    ActionTypeEnum(String type, Class<? extends AbstractAction> clazz) {
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
