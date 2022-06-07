package com.jflow.infra.impl.storage;

import com.jflow.infra.impl.utils.DbEntityUtil;
import com.jflow.infra.spi.storage.FlowSpecTunnel;
import com.jflow.infra.spi.storage.entity.FlowSpecEntity;
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
public class FlowSpecTunnelImpl extends AbstractTunnel<FlowSpecEntity> implements FlowSpecTunnel {

    public FlowSpecTunnelImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected Class<FlowSpecEntity> getEntityType() {
        return FlowSpecEntity.class;
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
