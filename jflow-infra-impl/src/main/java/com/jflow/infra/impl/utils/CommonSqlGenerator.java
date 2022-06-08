package com.jflow.infra.impl.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
public class CommonSqlGenerator {

    /**
     * Generate insert all sql.
     */
    public static String insertSql(Class<?> entity) {
        String tableName = DbEntityUtil.getTableName(entity);
        String prefix = "insert into ".concat(tableName);
        List<String> columns = DbEntityUtil.getColumns(entity, true);
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
        String tableName = DbEntityUtil.getTableName(entity);
        String prefix = "update ".concat(tableName).concat(" set ");
        List<String> columnWithoutId = DbEntityUtil.getColumns(entity, false);
        String mid = columnWithoutId.stream()
                .map(col -> col.concat(" = ?"))
                .collect(Collectors.joining(", "));
        String suffix = " where ".concat(DbEntityUtil.getIdName(entity)).concat(" = ?");
        return prefix.concat(mid).concat(suffix);
    }

}
