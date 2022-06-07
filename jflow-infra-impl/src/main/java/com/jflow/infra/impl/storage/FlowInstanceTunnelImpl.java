package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.FlowInstanceTunnel;
import com.jflow.infra.spi.storage.entity.FlowInstanceEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowInstanceTunnelImpl extends AbstractTunnel<FlowInstanceEntity> implements FlowInstanceTunnel {

    public FlowInstanceTunnelImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected Class<FlowInstanceEntity> getEntityType() {
        return FlowInstanceEntity.class;
    }

    @Override
    public int getRunningCountOfSpecId(String flowSpecId) {
        Integer count = jdbcTemplate.queryForObject("select count(1) from flow_ins where flow_spec_id = ?",
                Integer.class, flowSpecId);
        return count == null ? 0 : count;
    }
}
