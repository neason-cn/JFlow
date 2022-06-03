package com.jflow.core.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.auth.FlowUser;
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
     * @param args the data which created when start
     * @param user user
     * @return new flow instance
     */
    FlowInstance start(String flowSpecCode, JSONObject args, FlowUser user);

    /**
     * Start a flow instance by system(by sub_flow task).
     *
     * @param flowSpecCode flow spec code
     * @param args the data which created when start
     * @param user user
     * @param taskInstanceId parent task instance id
     * @return new flow instance
     */
    FlowInstance start(String flowSpecCode, JSONObject args, FlowUser user, String taskInstanceId);

    void fireNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user);

    void retryNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user);

    void skipNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user);

    void cancelNode(String flowInstanceId, String nodeId, JSONObject args, FlowUser user);

    void completeTask(String taskInstanceId, JSONObject args, FlowUser user);

    void terminate(String flowInstanceId, JSONObject args, FlowUser user);
}
