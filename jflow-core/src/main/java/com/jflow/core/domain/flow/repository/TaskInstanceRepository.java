package com.jflow.core.domain.flow.repository;

import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
import com.jflow.core.domain.flow.repository.serializer.TaskInstanceSerializer;
import com.jflow.infra.spi.storage.TaskInstanceTunnel;
import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.jflow.common.error.Errors.NO_TASK_INSTANCE_MATCHES_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class TaskInstanceRepository {

    private final TaskInstanceTunnel taskInstanceTunnel;
    private final TaskInstanceSerializer taskInstanceSerializer;

    public void save(TaskInstance instance) {
        taskInstanceTunnel.save(taskInstanceSerializer.serialize(instance));
    }

    public TaskInstance getById(String taskInstanceId) {
        Optional<TaskInstanceEntity> entity = taskInstanceTunnel.getById(taskInstanceId);
        if (!entity.isPresent()) {
            throw new FlowException(NO_TASK_INSTANCE_MATCHES_ERROR, taskInstanceId);
        }
        return taskInstanceSerializer.deSerialize(entity.get(), new TaskSpec());
    }

}
