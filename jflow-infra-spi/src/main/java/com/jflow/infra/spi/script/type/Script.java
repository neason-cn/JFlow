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
     * The type of script, such as QLExpress, Groovy, SpEL etc.
     */
    private String type;

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
     * @param type       script type
     * @param content    script content
     * @param resultType result type
     * @param <T>        result type
     * @return a instance extends Script<T>
     */
    public static <T> Script<T> of(String type, String content, TypeReference<T> resultType) {
        Script<T> script = new Script<T>() {
            @Override
            public TypeReference<T> getResultType() {
                return resultType;
            }
        };
        script.setType(type);
        script.setContent(content);
        return script;
    }

}
