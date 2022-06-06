package com.jflow.infra.spi.storage;

import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowInstanceTunnel {

    void save(FlowInstanceEntity entity);

    Optional<FlowInstanceEntity> getById(String flowInstanceId);

    int getRunningCountOfCode(String code);

}
