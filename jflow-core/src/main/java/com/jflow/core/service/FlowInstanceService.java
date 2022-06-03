package com.jflow.core.service;

import com.jflow.api.client.request.commands.FlowInstanceCommand;
import com.jflow.api.client.request.commands.FlowInstanceNodeCommand;
import com.jflow.api.client.request.commands.SaveDraftFlowSpecCommand;
import com.jflow.api.client.request.commands.StartFlowInstanceCommand;
import com.jflow.core.domain.flow.aggregate.FlowInstance;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowInstanceService {

    /**
     * Start a flow instance manually.
     *
     * @param command start command
     * @return new flow instance
     */
    FlowInstance start(StartFlowInstanceCommand command);

    /**
     * Start a flow instance by system(by sub_flow task).
     *
     * @param command        start command
     * @param taskInstanceId the sub_flow task instance id
     * @return new flow instance
     */
    FlowInstance start(SaveDraftFlowSpecCommand command, String taskInstanceId);

    void fireNode(FlowInstanceNodeCommand command);

    void retryNode(FlowInstanceNodeCommand command);

    void skipNode(FlowInstanceNodeCommand command);

    void cancelNode(FlowInstanceNodeCommand command);

    void terminate(FlowInstanceCommand command);
}
