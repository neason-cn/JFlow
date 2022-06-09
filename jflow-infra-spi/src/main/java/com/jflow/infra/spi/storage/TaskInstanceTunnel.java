package com.jflow.infra.spi.storage;

import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface TaskInstanceTunnel extends Tunnel<TaskInstanceEntity> {

    Optional<List<TaskInstanceEntity>> getByFlowInstanceId(String flowInstanceId);

}
