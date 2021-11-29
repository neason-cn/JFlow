package com.jflow.core.enums;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public enum StructTypeEnum {

    /**
     * 字符串
     */
    STRING("STRING"),

    /**
     * 整数
     */
    INTEGER("INTEGER"),

    /**
     * 长整数
     */
    LONG("LONG"),

    /**
     * 浮点数
     */
    FLOAT("FLOAT"),

    /**
     * 双精度小数
     */
    DOUBLE("DOUBLE"),

    /**
     * 布尔值
     */
    BOOLEAN("BOOLEAN"),

    /**
     * 数组/列表
     */
    ARRAY("ARRAY"),

    /**
     * 散列表
     */
    MAP("MAP"),

    /**
     * 复杂对象
     */
    OBJECT("OBJECT"),

    ;

    private final String type;

    StructTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
