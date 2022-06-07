package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.TaskInstanceTunnel;
import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class TaskInstanceTunnelImpl implements TaskInstanceTunnel {
    @Override
    public void save(TaskInstanceEntity entity) {

    }

    @Override
    public Optional<TaskInstanceEntity> getById(String id) {
        return Optional.empty();
    }
}
