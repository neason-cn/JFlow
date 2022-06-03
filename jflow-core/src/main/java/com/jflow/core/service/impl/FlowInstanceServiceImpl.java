package com.jflow.core.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.flow.aggregate.FlowInstance;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.factory.FlowInstanceFactory;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.flow.repository.FlowInstanceRepository;
import com.jflow.core.domain.flow.repository.FlowSpecRepository;
import com.jflow.core.service.FlowInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jflow.common.error.Errors.NO_RELEASED_FLOW_SPEC_VERSION_ERROR;
import static com.jflow.common.error.Errors.NO_SUCH_NODE_IN_FLOW;

/**
 * @author neason
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class FlowInstanceServiceImpl implements FlowInstanceService {

    private final FlowSpecRepository flowSpecRepository;
    private final FlowInstanceRepository flowInstanceRepository;
    private final FlowInstanceFactory flowInstanceFactory;

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject context, FlowUser user) {
        FlowInstance flowInstance = createAndInit(flowSpecCode, context, user, null);
        return null;
    }

    private FlowInstance createAndInit(String flowSpecCode, JSONObject context, FlowUser user, String taskInstanceId) {
        Optional<FlowSpec> flowSpec = flowSpecRepository.getReleaseByCode(flowSpecCode);

        FlowSpec spec = flowSpec.orElseThrow(() -> {
            throw new FlowException(NO_RELEASED_FLOW_SPEC_VERSION_ERROR, flowSpecCode);
        });

        return flowInstanceFactory.create(spec, context, user, taskInstanceId);
    }

    @Override
    public FlowInstance start(String flowSpecCode, JSONObject context, FlowUser user, String taskInstanceId) {
        FlowInstance flowInstance = createAndInit(flowSpecCode, context, user, taskInstanceId);
        return null;
    }

    AbstractNodeInstance getNodeOfFlow(String flowInstanceId, String nodeId) {
        FlowInstance flowInstance = flowInstanceRepository.getById(flowInstanceId);
        return flowInstance.findNode(nodeId)
                .orElseThrow(() -> {
                    throw new FlowException(NO_SUCH_NODE_IN_FLOW, nodeId, flowInstanceId);
                });
    }

    @Override
    public void fireNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user) {
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstanceId, nodeId);
    }


    @Override
    public void retryNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user) {
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstanceId, nodeId);
    }

    @Override
    public void skipNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user) {
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstanceId, nodeId);
    }

    @Override
    public void cancelNode(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user) {
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstanceId, nodeId);
    }

    @Override
    public void completeTask(String flowInstanceId, String nodeId, JSONObject jsonObject, FlowUser user) {
        AbstractNodeInstance nodeInstance = getNodeOfFlow(flowInstanceId, nodeId);
    }

    @Override
    public void terminate(String flowInstanceId, JSONObject jsonObject, FlowUser user) {

    }

}
