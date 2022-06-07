package com.jflow.common.utils;

import com.jflow.common.annotation.Column;
import com.jflow.common.annotation.Id;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public class DbEntityUtil {

    /**
     * Copy value from 'exist' to 'update' when some filed is null in 'update'.
     *
     * @param update entity for update
     * @param exist  exist entity
     * @param <T>    type
     * @return entity
     */
    public static <T> void copyWhenFieldIsNull(T update, T exist) {
        if (null == update) {
            return;
        }

        if (null == exist) {
            return;
        }

        for (Field field : update.getClass().getDeclaredFields()) {
            Object value = safeGetValue(field, update);
            if (null == value) {
                safeSetValue(field, update, safeGetValue(field, exist));
            }
        }
    }

    /**
     * Generate values for insert.
     */
    public static Object[] insertValues(Object entity) {
        return getValues(entity, true);
    }

    /**
     * Generate values for update.
     */
    public static Object[] updateValues(Object entity) {
        return getValues(entity, false);
    }

    private static Object[] getValues(Object entity, boolean insert) {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        Object[] values = new Object[declaredFields.length];
        int i = 0;
        Object idValue = null;
        for (Field field : declaredFields) {
            Id id = field.getAnnotation(Id.class);
            if (null != id) {
                Object value = safeGetValue(field, entity);
                idValue = value;
                if (insert) {
                    values[i] = value;
                    i++;
                }
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            if (null != column) {
                values[i] = safeGetValue(field, entity);
                i++;
            }
        }
        if (!insert) {
            values[i] = idValue;
        }
        return values;
    }


    /**
     * Get first element of the list.
     *
     * @param entities list
     * @param <T>      element type
     * @return first element
     */
    public static <T> Optional<T> getFirst(List<T> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Optional.empty();
        }
        return Optional.of(entities.get(0));
    }

    private static Object safeGetValue(Field field, Object target) {
        boolean accessible = field.isAccessible();

        if (accessible) {
            try {
                return field.get(target);
            } catch (IllegalAccessException e) {
                return null;
            }
        }

        try {
            field.setAccessible(true);
            Object value = field.get(target);
            field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static void safeSetValue(Field field, Object target, Object value) {
        boolean accessible = field.isAccessible();
        if (accessible) {
            try {
                field.set(target, value);
            } catch (IllegalAccessException e) {
                return;
            }
        }

        try {
            field.setAccessible(true);
            field.set(target, value);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            // ignore
        }
    }

}
