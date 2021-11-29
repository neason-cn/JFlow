package com.jflow.core.enums;

/**
 * 内部的Command类型
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public enum InnerCommandTypeEnum {

    /**
     * 开始/发起一个新的FlowInstance
     */
    START("START"),

    /**
     * 跳过FlowInstance的当前节点
     */
    SKIP("SKIP"),

    /**
     * 推进FlowInstance
     */
    RESUME("RESUME"),

    /**
     * 取消FlowInstance
     */
    CANCEL("CANCEL"),

    /**
     * 完结FlowInstance
     */
    FINISH("FINISH"),

    ;

    private final String type;

    InnerCommandTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
