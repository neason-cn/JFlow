package com.jflow.core.event.command;

import java.util.Map;

import com.jflow.core.event.FlowEvent;

/**
 * 取消执行当前FlowInstance
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class CancelCommand implements FlowEvent<CancelCommand> {
    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public CancelCommand getPayload() {
        return this;
    }
}
