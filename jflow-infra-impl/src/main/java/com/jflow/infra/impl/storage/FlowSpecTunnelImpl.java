package com.jflow.infra.impl.storage;

import com.jflow.common.utils.DbEntityUtil;
import com.jflow.infra.spi.storage.FlowSpecTunnel;
import com.jflow.infra.spi.storage.entity.FlowSpecEntity;
import lombok.RequiredArgsConstructor;
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
public class FlowSpecTunnelImpl implements FlowSpecTunnel {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "insert into flow_spec(spec_id, spec_code, spec_version, spec_desc, spec_status, enable_multi_instance," +
            "init_context, output_script, scheduled, cron, start_action, end_action, nodes, edges, create_at, release_at) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String UPDATE_SQL = "update flow_spec set spec_id = ?, spec_code = ?, spec_version = ?, spec_status = ?," +
            "enable_multi_instance = ?, output_script = ?, init_context = ?, scheduled = ?, cron = ?, start_action = ?, end_action = ?, nodes = ?," +
            "edges = ?, create_at = ?, release_at = ?";

    @Override
    public void save(FlowSpecEntity entity) {
        Optional<FlowSpecEntity> exist = getById(entity.getSpecId());
        if (exist.isPresent()) {
            DbEntityUtil.copyWhenFieldIsNull(entity, exist);
            jdbcTemplate.update(UPDATE_SQL, DbEntityUtil.getAllFields(entity));
        }
        jdbcTemplate.update(INSERT_SQL, DbEntityUtil.getAllFields(entity));
    }

    @Override
    public Optional<FlowSpecEntity> getById(String specId) {
        List<FlowSpecEntity> entities = jdbcTemplate.query("select * from flow_spec where spec_id = ?",
                new BeanPropertyRowMapper<>(FlowSpecEntity.class), specId);
        return DbEntityUtil.getFirst(entities);
    }

    @Override
    public Optional<FlowSpecEntity> getLatestVersionByCode(String code) {
        List<FlowSpecEntity> entities = jdbcTemplate.query("select * from flow_spec where spec_code = ? order by spec_version desc",
                new BeanPropertyRowMapper<>(FlowSpecEntity.class), code);
        return DbEntityUtil.getFirst(entities);
    }

    @Override
    public Optional<FlowSpecEntity> getReleaseByCode(String code) {
        List<FlowSpecEntity> entities = jdbcTemplate.query("select * from flow_spec where spec_code = ? and spec_status = 'RELEASED'",
                new BeanPropertyRowMapper<>(FlowSpecEntity.class), code);
        return DbEntityUtil.getFirst(entities);
    }
}
