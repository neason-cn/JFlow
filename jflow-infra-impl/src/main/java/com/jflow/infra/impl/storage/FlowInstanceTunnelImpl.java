package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.FlowInstanceTunnel;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowInstanceTunnelImpl implements FlowInstanceTunnel {
    @Override
    public void save(FlowInstanceEntity entity) {

    }

    @Override
    public Optional<FlowInstanceEntity> getById(String flowInstanceId) {
        return Optional.empty();
    }

    @Override
    public int getRunningCountOfCode(String code) {
        return 0;
    }
}
