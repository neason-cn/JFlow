package com.jflow.core.event.command;

import java.util.Map;

import com.jflow.core.event.FlowEvent;

/**
 * 结束/完成 当前FlowInstance
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class FinishCommand implements FlowEvent<FinishCommand> {
    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public FinishCommand getPayload() {
        return this;
    }
}
