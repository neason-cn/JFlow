package com.jflow.core.engine.enums.status;

import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum EdgeInstanceStatusEnum {

    INIT("INIT", false),

    ALLOW("ALLOW", true),

    DENY("DENY", false),

    ERROR("ERROR", false);

    @Getter
    private final String status;
    @Getter
    private final boolean access;

    EdgeInstanceStatusEnum(String status, boolean access) {
        this.status = status;
        this.access = access;
    }

    public static EdgeInstanceStatusEnum of(String status) {
        for (EdgeInstanceStatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(EdgeInstanceStatusEnum.class, status);
    }
}
