package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.FlowInstanceTunnel;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowInstanceTunnelImpl implements FlowInstanceTunnel {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(FlowInstanceEntity entity) {

    }

    @Override
    public Optional<FlowInstanceEntity> getById(String flowInstanceId) {
        List<FlowInstanceEntity> entity = jdbcTemplate.query("select * from flow_ins where flow_instance_id = ?",
                new BeanPropertyRowMapper<>(FlowInstanceEntity.class), flowInstanceId);
        if (CollectionUtils.isEmpty(entity)) {
            return Optional.empty();
        }
        return Optional.of(entity.get(0));
    }

    @Override
    public int getRunningCountOfSpecId(String flowSpecId) {
        Integer count = jdbcTemplate.queryForObject("select count(1) form flow_ins where flow_spec_id = ?",
                Integer.class, flowSpecId);
        return count == null ? 0 : count;
    }
}
