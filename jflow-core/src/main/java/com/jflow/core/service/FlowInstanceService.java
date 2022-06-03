package com.jflow.core.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.api.client.request.commands.*;
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
     * @param context the data which created when start
     * @param user user
     * @return new flow instance
     */
    FlowInstance start(String flowSpecCode, JSONObject context, FlowUser user);

    /**
     * Start a flow instance by system(by sub_flow task).
     *
     * @param flowSpecCode flow spec code
     * @param context the data which created when start
     * @param user user
     * @param taskInstanceId parent task instance id
     * @return new flow instance
     */
    FlowInstance start(String flowSpecCode, JSONObject context, FlowUser user, String taskInstanceId);

    void fireNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user);

    void retryNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user);

    void skipNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user);

    void cancelNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user);

    void completeTask(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user);

    void terminate(String flowInstanceId, JSONObject jsonObject, FlowUser user);
}
