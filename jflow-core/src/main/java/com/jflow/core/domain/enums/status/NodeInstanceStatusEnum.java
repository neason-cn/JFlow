package com.jflow.core.domain.enums.status;

import com.jflow.common.enums.Status;
import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum NodeInstanceStatusEnum implements Status {

    INIT("INIT");

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
