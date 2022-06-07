package com.jflow.infra.impl.storage;

import com.jflow.common.annotation.Id;
import com.jflow.common.exception.FlowException;
import com.jflow.infra.impl.utils.DbEntityUtil;
import com.jflow.infra.impl.utils.DbSqlUtil;
import com.jflow.infra.spi.storage.Tunnel;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static com.jflow.common.error.Errors.GENERATE_SQL_ERROR;

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
                DbSqlUtil.getTableName(getEntityType()), DbSqlUtil.getIdName(getEntityType()));
        List<T> entities = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityType()), id);
        return DbEntityUtil.getFirst(entities);
    }

    @Override
    public void save(T entity) {
        Optional<T> exist = getById(getId(entity));
        if (exist.isPresent()) {
            DbEntityUtil.copyWhenFieldIsNull(entity, exist.get());
            jdbcTemplate.update(DbSqlUtil.updateSql(entity.getClass()), DbEntityUtil.updateValues(entity));
            return;
        }
        jdbcTemplate.update(DbSqlUtil.insertSql(entity.getClass()), DbEntityUtil.insertValues(entity));
    }

    private String getId(T entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            Id id = field.getAnnotation(Id.class);
            if (null != id) {
                Object value = DbEntityUtil.safeGetValue(field, entity);
                return (String) value;
            }
        }
        throw new FlowException(GENERATE_SQL_ERROR, "no filed has @Id annotation.");
    }

    protected abstract Class<T> getEntityType();

}
