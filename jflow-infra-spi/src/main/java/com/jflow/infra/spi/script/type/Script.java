package com.jflow.infra.spi.script.type;

import com.alibaba.fastjson2.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The string script content wrapper.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
public abstract class Script<T> {

    /**
     * The content of script.
     */
    private String content;

    /**
     * @return The class type of the script result.
     */
    public abstract TypeReference<T> getResultType();

    /**
     * Create a script contains generic type.
     *
     * @param content    script content
     * @param resultType result type
     * @param <T>        result type
     * @return a instance extends Script<T>
     */
    public static <T> Script<T> of(String content, TypeReference<T> resultType) {
        return new Script<T>(content) {
            @Override
            public TypeReference<T> getResultType() {
                return resultType;
            }
        };
    }

}
