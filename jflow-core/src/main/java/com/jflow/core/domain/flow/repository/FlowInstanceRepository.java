package com.jflow.core.domain.flow.repository;

import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.flow.aggregate.FlowInstance;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.repository.serializer.FlowInstanceSerializer;
import com.jflow.infra.spi.storage.FlowInstanceTunnel;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.jflow.common.error.Errors.NO_FLOW_INSTANCE_MATCHES_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowInstanceRepository {

    private final FlowInstanceTunnel flowInstanceTunnel;
    private final FlowInstanceSerializer flowInstanceSerializer;
    private final TaskInstanceRepository taskInstanceRepository;

    public void save(FlowInstance flowInstance) {
        flowInstanceTunnel.save(flowInstanceSerializer.serialize(flowInstance));
    }

    public FlowInstance getByIdWithoutSpec(String flowInstanceId) {
        Optional<FlowInstanceEntity> entity = flowInstanceTunnel.getById(flowInstanceId);
        if (!entity.isPresent()) {
            throw new FlowException(NO_FLOW_INSTANCE_MATCHES_ERROR, flowInstanceId);
        }
        return flowInstanceSerializer.deSerialize(entity.get(), null);
    }

    public int getRunningCountOfSpecId(String flowSpecId) {
        return flowInstanceTunnel.getRunningCountOfSpecId(flowSpecId);
    }

    public FlowInstance getByTaskId(String taskInstanceId) {
        TaskInstance taskInstance = taskInstanceRepository.getById(taskInstanceId);
        return getByIdWithoutSpec(taskInstance.getFlowInstanceId());
    }

}
