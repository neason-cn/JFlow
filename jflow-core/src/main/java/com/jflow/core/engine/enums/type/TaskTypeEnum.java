package com.jflow.core.engine.enums.type;

import com.jflow.common.enums.Type;
import lombok.Getter;

/**
 * The enum of task type.
 *
 * @author neason
 * @since 0.0.1
 */
public enum TaskTypeEnum implements Type {

    /**
     * Synchronized task.
     */
    SYNC("SYNC"),

    /**
     * Asynchronous task.
     */
    ASYNC("ASYNC");

    @Getter
    private final String type;

    TaskTypeEnum(String type) {
        this.type = type;
    }

    public static TaskTypeEnum of(String type) {
        for (TaskTypeEnum value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(TaskTypeEnum.class, type);
    }

}
