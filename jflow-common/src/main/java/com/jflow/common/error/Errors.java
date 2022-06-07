package com.jflow.common.error;

import lombok.Getter;

/**
 * @author neason
 * @since 0.0.1
 */
public enum Errors implements FlowError {

    SYSTEM_ERROR("SYSTEM_ERROR", "{}"),

    // Flow Spec
    NO_FLOW_SPEC_MATCHES_ERROR("NO_FLOW_SPEC_MATCHES_ERROR", "no flow spec of the id: {}"),
    NO_RELEASED_FLOW_SPEC_VERSION_ERROR("NO_RELEASED_FLOW_SPEC_VERSION_ERROR", "no released version of this spec code: {}"),
    ILLEGAL_FLOW_SPEC_STATUS_ERROR("ILLEGAL_FLOW_SPEC_STATUS_ERROR", "the status {} of flowSpec {} is illegal, {}"),
    UNSUPPORTED_MULTI_INSTANCE_ERROR("UNSUPPORTED_MULTI_INSTANCE_ERROR", "can not create multi instance of the spec code: {}"),

    // Flow Instance
    UNSUPPORTED_NODE_OPERATION_ERROR("UNSUPPORTED_NODE_OPERATION_ERROR", "the node type: {} can not action {}"),
    NO_FLOW_INSTANCE_MATCHES_ERROR("NO_FLOW_INSTANCE_MATCHES_ERROR", "no flow instance matches the id: {}"),
    NO_SUCH_NODE_IN_FLOW("NO_SUCH_NODE_IN_FLOW", "no nodeId: {} matched in the flow : {}"),
    NO_NODE_CONTAINS_TASK_ERROR("NO_NODE_CONTAINS_TASK_ERROR", "no node contains the task: {}"),

    // Task Instance
    NO_TASK_INSTANCE_MATCHES_ERROR("NO_TASK_INSTANCE_MATCHES_ERROR", "no task instance of the id: {}"),

    // DB
    GENERATE_SQL_ERROR("GENERATE_SQL_ERROR", "auto generate sql error: {}"),

    // Other
    NODE_INSTANCE_CLASS_NEW_ERROR("NODE_INSTANCE_CLASS_NEW_ERROR", "class {} new instance error}");

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
