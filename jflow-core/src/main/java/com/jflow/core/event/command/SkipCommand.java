package com.jflow.core.event.command;

import java.util.Map;

import com.jflow.core.event.FlowEvent;

/**
 * 跳过当前节点
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class SkipCommand implements FlowEvent<SkipCommand> {
    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public SkipCommand getPayload() {
        return this;
    }
}
