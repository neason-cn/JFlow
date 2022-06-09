package com.jflow.infra.impl.storage;

import com.jflow.infra.spi.storage.TaskInstanceTunnel;
import com.jflow.infra.spi.storage.entity.TaskInstanceEntity;
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
public class TaskInstanceTunnelImpl extends AbstractTunnel<TaskInstanceEntity> implements TaskInstanceTunnel {

    public TaskInstanceTunnelImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected Class<TaskInstanceEntity> getEntityType() {
        return TaskInstanceEntity.class;
    }

    @Override
    public Optional<List<TaskInstanceEntity>> getByFlowInstanceId(String flowInstanceId) {
        List<TaskInstanceEntity> entities = jdbcTemplate.query("select * from task_ins where flow_instance_id = ?",
                new BeanPropertyRowMapper<>(TaskInstanceEntity.class), flowInstanceId);
        if (CollectionUtils.isEmpty(entities)) {
            return Optional.empty();
        }
        return Optional.of(entities);
    }
}
