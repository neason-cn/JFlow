package com.jflow.common.utils;

import com.jflow.common.annotation.Column;
import com.jflow.common.annotation.Id;
import com.jflow.common.annotation.Table;
import com.jflow.common.exception.FlowException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jflow.common.error.Errors.GENERATE_SQL_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
public class DbSqlUtil {

    /**
     * Generate insert all sql.
     */
    public static String insertSql(Class<?> entity) {
        String tableName = getTableName(entity);
        String prefix = "insert into ".concat(tableName);
        List<String> columns = getColumns(entity, true);
        String mid = " (".concat(String.join(", ", columns)).concat(")");
        String placeHolder = columns.stream()
                .map(col -> "?")
                .collect(Collectors.joining(", "));

        String suffix = " values (".concat(placeHolder).concat(")");
        return prefix.concat(mid).concat(suffix);
    }

    /**
     * Generate update all by id sql.
     */
    public static String updateSql(Class<?> entity) {
        String tableName = getTableName(entity);
        String prefix = "update ".concat(tableName).concat(" set ");
        List<String> columnWithoutId = getColumns(entity, false);
        String mid = columnWithoutId.stream()
                .map(col -> col.concat(" = ?"))
                .collect(Collectors.joining(", "));
        String suffix = " where ".concat(getIdName(entity)).concat(" = ?");
        return prefix.concat(mid).concat(suffix);
    }

    private static String getTableName(Class<?> entity) {
        Table annotation = entity.getAnnotation(Table.class);
        if (null == annotation) {
            throw new FlowException(GENERATE_SQL_ERROR, "entity has no @Table annotation.");
        }
        return annotation.value();
    }

    private static String getIdName(Class<?> entity) {
        Optional<String> idName = Arrays.stream(entity.getDeclaredFields())
                .map(field -> {
                    Id id = field.getAnnotation(Id.class);
                    if (null != id) {
                        return id.value();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .findAny();
        if (idName.isPresent()) {
            return idName.get();
        }
        throw new FlowException(GENERATE_SQL_ERROR, "no field has @Id annotation.");
    }

    private static List<String> getColumns(Class<?> entity, boolean withId) {
        return Arrays.stream(entity.getDeclaredFields())
                .map(field -> {
                    Column column = field.getAnnotation(Column.class);
                    if (null == column) {
                        if (withId) {
                            Id id = field.getAnnotation(Id.class);
                            if (null != id) {
                                return id.value();
                            }
                        }
                        return null;
                    }
                    return column.value();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
