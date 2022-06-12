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
    UNSUPPORTED_NODE_OPERATION_ERROR("UNSUPPORTED_NODE_OPERATION_ERROR", "the node can not action {}"),
    NO_FLOW_INSTANCE_MATCHES_ERROR("NO_FLOW_INSTANCE_MATCHES_ERROR", "no flow instance matches the id: {}"),
    NO_SUCH_NODE_IN_FLOW("NO_SUCH_NODE_IN_FLOW", "no nodeId: {} matched in the flow : {}"),
    NO_SUCH_EDGE_IN_FLOW("NO_SUCH_EDGE_IN_FLOW", "no edgeId: {} matched in the flow : {}"),
    NO_NODE_CONTAINS_TASK_ERROR("NO_NODE_CONTAINS_TASK_ERROR", "no node contains the task: {}"),

    // Task Instance
    NO_TASK_INSTANCE_MATCHES_ERROR("NO_TASK_INSTANCE_MATCHES_ERROR", "no task instance of the id: {}"),

    // DB
    GENERATE_SQL_ERROR("GENERATE_SQL_ERROR", "auto generate sql error: {}"),

    // Infra
    EMPTY_SCHEDULER_JOB_WORKER_ERROR("EMPTY_JOB_WORKER_ERROR", "job runner can not be null"),
    CREATE_SCHEDULER_JOB_ERROR("CREATE_SCHEDULER_JOB_ERROR", "create scheduler job error: {}"),
    NO_SUCH_SCHEDULER_JOB_ERROR("NO_SUCH_SCHEDULER_JOB_ERROR", "no job id of : {}"),

    // Other
    TASK_INSTANCE_CLASS_NEW_ERROR("TASK_INSTANCE_CLASS_NEW_ERROR", "task class {} new instance error}"),
    NODE_INSTANCE_CLASS_NEW_ERROR("NODE_INSTANCE_CLASS_NEW_ERROR", "node class {} new instance error}");

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
