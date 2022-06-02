package com.jflow.common.error;

import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum Errors implements FlowError {

    SYSTEM_ERROR("SYSTEM_ERROR", "system error, {}"),

    // Flow Spec
    NO_RELEASED_FLOW_SPEC_VERSION_ERROR("NO_RELEASED_FLOW_SPEC_VERSION_ERROR","no released version of this spec code: {}"),
    ILLEGAL_FLOW_SPEC_STATUS_ERROR("ILLEGAL_FLOW_SPEC_STATUS_ERROR", "the status {} of flowSpec {} is illegal, {}");

    @Getter
    private final String code;
    @Getter
    private final String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String errorCode() {
        return this.code;
    }

    @Override
    public String errorMessage() {
        return this.message;
    }
}
