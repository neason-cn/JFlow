package com.jflow.core.engine.enums.status;

import com.jflow.common.enums.Status;
import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum FlowSpecStatusEnum implements Status {

    /**
     * The flow spec can be edited multi times and can not use the 'DRAFT' version spec to create a flow instance.
     */
    DRAFT("DRAFT"),

    /**
     * A flow spec can only have one 'RELEASED' version.
     */
    RELEASED("RELEASED"),

    /**
     * The old spec will be 'ARCHIVED' when a new spec went into 'RELEASED'.
     */
    ARCHIVED("ARCHIVED");

    @Getter
    private final String status;

    FlowSpecStatusEnum(String status) {
        this.status = status;
    }

    public static FlowSpecStatusEnum of(String status) {
        for (FlowSpecStatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(FlowSpecStatusEnum.class, status);
    }

}
