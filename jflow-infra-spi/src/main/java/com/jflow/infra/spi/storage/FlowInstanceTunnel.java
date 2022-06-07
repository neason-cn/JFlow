package com.jflow.infra.spi.storage;

import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowInstanceTunnel extends Tunnel<FlowInstanceEntity> {

    int getRunningCountOfSpecId(String code);

}
