package com.jflow.infra.spi.script;

import lombok.Getter;

/**
 * The enum of script content.
 *
 * @author neason
 * @since 0.0.1
 */
public enum ScriptEnum {

    QL_EXPRESS("QL_EXPRESS"),

    GROOVY("GROOVY"),

    JAVA_SCRIPT("JAVA_SCRIPT");
    @Getter
    private final String type;

    ScriptEnum(String type) {
        this.type = type;
    }

    public static ScriptEnum of(String type) {
        for (ScriptEnum value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(ScriptEnum.class, type);
    }

}
