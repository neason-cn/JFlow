package com.jflow.infra.impl.storage;

import com.jflow.infra.impl.utils.DbEntityUtil;
import com.jflow.infra.impl.utils.CommonSqlGenerator;
import com.jflow.infra.spi.storage.Tunnel;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@AllArgsConstructor
public abstract class AbstractTunnel<T> implements Tunnel<T> {

    protected final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<T> getById(String id) {
        String sql = String.format("select * from %s where %s = ?",
                DbEntityUtil.getTableName(getEntityType()), DbEntityUtil.getIdName(getEntityType()));
        List<T> entities = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityType()), id);
        return DbEntityUtil.getFirst(entities);
    }

    @Override
    public void save(T entity) {
        Optional<T> exist = getById(DbEntityUtil.getIdValue(entity));
        if (exist.isPresent()) {
            DbEntityUtil.copyWhenFieldIsNull(entity, exist.get());
            jdbcTemplate.update(CommonSqlGenerator.updateSql(entity.getClass()), DbEntityUtil.updateValues(entity));
            return;
        }
        jdbcTemplate.update(CommonSqlGenerator.insertSql(entity.getClass()), DbEntityUtil.insertValues(entity));
    }

    protected abstract Class<T> getEntityType();

}
