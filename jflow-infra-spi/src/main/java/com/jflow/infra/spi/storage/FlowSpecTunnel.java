package com.jflow.infra.spi.storage;

import com.jflow.infra.spi.storage.entity.FlowSpecEntity;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecTunnel {

    void save(FlowSpecEntity entity);

    Optional<FlowSpecEntity> getById(String specId);

    Optional<FlowSpecEntity> getLatestVersionByCode(String code);

    Optional<FlowSpecEntity> getReleaseByCode(String code);

}
