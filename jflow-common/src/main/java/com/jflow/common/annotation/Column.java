package com.jflow.common.annotation;

import java.lang.annotation.*;

/**
 * @author neason
 * @since 0.0.1
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value();
}
