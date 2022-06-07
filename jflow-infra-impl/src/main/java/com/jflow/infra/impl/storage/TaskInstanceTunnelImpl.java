package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.TaskInstanceTunnel;
import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class TaskInstanceTunnelImpl extends AbstractTunnel<TaskInstanceEntity> implements TaskInstanceTunnel {

    public TaskInstanceTunnelImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected Class<TaskInstanceEntity> getEntityType() {
        return TaskInstanceEntity.class;
    }

}
