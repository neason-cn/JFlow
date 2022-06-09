package com.jflow.core.engine.enums.status;

import com.jflow.common.enums.Status;
import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum TaskInstanceStatusEnum implements Status {

    INIT("INIT"),

    RUNNING("RUNNING"),

    SUCCESS("SUCCESS"),

    FAILED("FAILED");

    @Getter
    private final String status;

    TaskInstanceStatusEnum(String status) {
        this.status = status;
    }

    public static TaskInstanceStatusEnum of(String status) {
        for (TaskInstanceStatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(TaskInstanceStatusEnum.class, status);
    }

}
