package com.jflow.core.engine.enums.status;

import com.jflow.common.enums.Status;
import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum FlowInstanceStatusEnum implements Status {

    INIT("INIT");

    @Getter
    private final String status;

    FlowInstanceStatusEnum(String status) {
        this.status = status;
    }

    public static FlowInstanceStatusEnum of(String status) {
        for (FlowInstanceStatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(FlowInstanceStatusEnum.class, status);
    }

}
