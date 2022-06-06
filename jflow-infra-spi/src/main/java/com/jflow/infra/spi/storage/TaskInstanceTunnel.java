package com.jflow.infra.spi.storage;

import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface TaskInstanceTunnel {

    void save(TaskInstanceEntity entity);

    Optional<TaskInstanceEntity> getById(String taskInstanceId);

}
