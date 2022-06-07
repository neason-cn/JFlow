package com.jflow.core.domain.repository;

import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.serializer.FlowInstanceSerializer;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import com.jflow.core.engine.flow.instance.TaskInstance;
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
    private final FlowSpecRepository flowSpecRepository;
    private final TaskInstanceRepository taskInstanceRepository;

    public void save(FlowInstance flowInstance) {
        flowInstanceTunnel.save(flowInstanceSerializer.serialize(flowInstance));
    }

    public FlowInstance getById(String flowInstanceId) {
        Optional<FlowInstanceEntity> entity = flowInstanceTunnel.getById(flowInstanceId);
        if (!entity.isPresent()) {
            throw new FlowException(NO_FLOW_INSTANCE_MATCHES_ERROR, flowInstanceId);
        }
        FlowSpec flowSpec = flowSpecRepository.getById(entity.get().getFlowSpecId());
        return flowInstanceSerializer.deSerialize(entity.get(), flowSpec);
    }

    public int getRunningCountOfSpecId(String flowSpecId) {
        return flowInstanceTunnel.getRunningCountOfSpecId(flowSpecId);
    }

    public FlowInstance getByTaskId(String taskInstanceId) {
        TaskInstance taskInstance = taskInstanceRepository.getById(taskInstanceId);
        return getById(taskInstance.getFlowInstanceId());
    }

}
