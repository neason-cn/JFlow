package com.jflow.common.log;

import org.slf4j.MDC;

/**
 * @author neason
 * @since 0.0.1
 */
public class LogContext {

    private final static String FLOW_INSTANCE_ID = "fi";
    private final static String NODE_ID = "ni";
    private final static String TASK_INSTANCE_ID = "ti";
    private final static String ACTION_INSTANCE_ID = "ai";

    public static void fi(String flowInstanceId) {
        MDC.put(FLOW_INSTANCE_ID, flowInstanceId);
    }

    public static void ni(String nodeId) {
        MDC.put(NODE_ID, nodeId);
    }

    public static void ti(String taskInstanceId) {
        MDC.put(TASK_INSTANCE_ID, taskInstanceId);
    }

    public static void ai(String actionInstanceId) {
        MDC.put(ACTION_INSTANCE_ID, actionInstanceId);
    }

}
