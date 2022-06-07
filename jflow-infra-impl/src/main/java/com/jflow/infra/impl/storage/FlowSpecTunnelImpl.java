package com.jflow.infra.impl.storage;

import com.jflow.common.utils.DbEntityUtil;
import com.jflow.common.utils.DbSqlUtil;
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

    @Override
    public Optional<FlowSpecEntity> getById(String id) {
        List<FlowSpecEntity> entities = jdbcTemplate.query("select * from flow_spec where spec_id = ?",
                new BeanPropertyRowMapper<>(FlowSpecEntity.class), id);
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

    @Override
    public void save(FlowSpecEntity entity) {
        Optional<FlowSpecEntity> exist = getById(entity.getSpecId());
        if (exist.isPresent()) {
            DbEntityUtil.copyWhenFieldIsNull(entity, exist.get());
            jdbcTemplate.update(DbSqlUtil.updateSql(entity.getClass()), DbEntityUtil.updateValues(entity));
            return;
        }
        jdbcTemplate.update(DbSqlUtil.insertSql(entity.getClass()), DbEntityUtil.insertValues(entity));
    }

}
