package com.jflow.core.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.flow.aggregate.FlowInstance;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowInstanceService {

    /**
     * Start a flow instance manually.
     *
     * @param flowSpecCode flow spec code
     * @param args         the data which created when start
     * @return new flow instance
     */
    FlowInstance start(String flowSpecCode, JSONObject args);

    /**
     * Start a flow instance by system(by sub_flow task).
     *
     * @param flowSpecCode   flow spec code
     * @param args           the data which created when start
     * @param taskInstanceId parent task instance id
     * @return new flow instance
     */
    FlowInstance start(String flowSpecCode, JSONObject args, String taskInstanceId);

    void fireNode(String flowInstanceId, String nodeId, JSONObject args);

    void retryNode(String flowInstanceId, String nodeId, JSONObject args);

    void skipNode(String flowInstanceId, String nodeId, JSONObject args);

    void cancelNode(String flowInstanceId, String nodeId, JSONObject args);

    void completeTask(String taskInstanceId, JSONObject args);

    void terminate(String flowInstanceId, JSONObject args);
}
