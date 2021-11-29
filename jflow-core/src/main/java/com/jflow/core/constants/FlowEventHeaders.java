package com.jflow.core.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class FlowEventHeaders {

    private final static String FLOW_PREFIX = "x-flow-";
    public final static String COMMAND_TYPE = FLOW_PREFIX + "command-type";
    public final static String COMMAND_DATA_TYPE = FLOW_PREFIX + "command-data-type";
    public final static String BIZ_EVENT_TYPE = FLOW_PREFIX + "biz-event-type";
    public final static String TRACE_ID = FLOW_PREFIX + "trace-id";
    public final static String FLOW_INSTANCE_ID = FLOW_PREFIX + "instance-id";
    public final static String FLOW_NODE_ID = FLOW_PREFIX + "node-id";
    public final static List<String> INNER_HEADER_KEYS = new ArrayList<>();

    static {
        INNER_HEADER_KEYS.add(COMMAND_TYPE);
        INNER_HEADER_KEYS.add(COMMAND_DATA_TYPE);
        INNER_HEADER_KEYS.add(TRACE_ID);
        INNER_HEADER_KEYS.add(FLOW_INSTANCE_ID);
        INNER_HEADER_KEYS.add(FLOW_NODE_ID);
    }

}
