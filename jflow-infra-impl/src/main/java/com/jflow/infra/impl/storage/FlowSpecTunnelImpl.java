package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.FlowSpecTunnel;
import com.jflow.infra.spi.storage.entity.FlowSpecEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowSpecTunnelImpl implements FlowSpecTunnel {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(FlowSpecEntity entity) {

    }

    @Override
    public Optional<FlowSpecEntity> getById(String specId) {
        return Optional.empty();
    }

    @Override
    public Optional<FlowSpecEntity> getLatestVersionByCode(String code) {
        return Optional.empty();
    }

    @Override
    public Optional<FlowSpecEntity> getReleaseByCode(String code) {
        return Optional.empty();
    }
}
