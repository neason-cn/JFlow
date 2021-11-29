package com.jflow.core.event.command;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.jflow.core.constants.FlowEventHeaders;
import com.jflow.core.enums.InnerCommandTypeEnum;
import com.jflow.core.event.FlowEvent;

/**
 * Flow内部的操作Command
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public abstract class FlowInnerCommand<T> implements FlowEvent<T> {

    protected final JSONObject input;
    private final Map<String, String> headers;
    private final T payload;

    public FlowInnerCommand(JSONObject input) {
        this.input = input;
        this.headers = extractHeaders(input);
        this.payload = extractPayload(input);
    }

    private Map<String, String> extractHeaders(JSONObject input) {
        Map<String, String> headers = new HashMap<>(8);
        for (String key : FlowEventHeaders.INNER_HEADER_KEYS) {
            String value = input.getString(key);
            headers.put(key, value);
        }
        return headers;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public T getPayload() {
        return this.payload;
    }

    /**
     * 将command中的参数集合转为强类型
     *
     * @param input JSONObject
     * @return T
     */
    protected abstract T extractPayload(JSONObject input);

    /**
     * 返回Command的类型
     *
     * @return InnerCommandTypeEnum
     */
    public abstract InnerCommandTypeEnum getCommandType();

}
