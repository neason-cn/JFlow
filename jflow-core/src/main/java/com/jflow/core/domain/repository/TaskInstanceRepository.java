package com.jflow.core.domain.repository;

import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.serializer.TaskInstanceSerializer;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.TaskSpec;
import com.jflow.infra.spi.storage.TaskInstanceTunnel;
import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<List<TaskInstance>> getByFlowInstanceId(String flowInstanceId) {
        Optional<List<TaskInstanceEntity>> entities = taskInstanceTunnel.getByFlowInstanceId(flowInstanceId);
        if (entities.isPresent()) {
            List<TaskInstanceEntity> tasks = entities.get();
            List<TaskInstance> instances = tasks.stream()
                    .map(entity -> taskInstanceSerializer.deSerialize(entity, null))
                    .collect(Collectors.toList());
            return Optional.of(instances);
        }
        return Optional.empty();
    }

}
