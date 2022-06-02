package com.jflow.infra.spi.script.type;

import com.alibaba.fastjson2.TypeReference;
import lombok.Data;

/**
 * The string script content wrapper.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
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
        Script<T> script = new Script<T>() {
            @Override
            public TypeReference<T> getResultType() {
                return resultType;
            }
        };
        script.setContent(content);
        return script;
    }

}
