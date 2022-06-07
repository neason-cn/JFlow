package com.jflow.infra.spi.storage;

import com.jflow.infra.spi.storage.entity.FlowSpecEntity;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecTunnel extends Tunnel<FlowSpecEntity> {

    Optional<FlowSpecEntity> getLatestVersionByCode(String code);

    Optional<FlowSpecEntity> getReleaseByCode(String code);

}
