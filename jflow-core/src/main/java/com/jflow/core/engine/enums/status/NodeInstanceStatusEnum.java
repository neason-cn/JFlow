package com.jflow.core.engine.enums.status;

import com.jflow.common.enums.Status;
import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum NodeInstanceStatusEnum implements Status {

    INIT("INIT"),

    WAITING("WAITING"),

    IDLE("IDLE"),

    SKIPPED("SKIPPED"),

    SUCCESS("SUCCESS"),

    RUNNING("RUNNING"),

    FAILED("FAILED"),

    CANCELED("CANCELED")
    ;

    @Getter
    private final String status;

    NodeInstanceStatusEnum(String status) {
        this.status = status;
    }

    public static NodeInstanceStatusEnum of(String status) {
        for (NodeInstanceStatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(NodeInstanceStatusEnum.class, status);
    }

}
