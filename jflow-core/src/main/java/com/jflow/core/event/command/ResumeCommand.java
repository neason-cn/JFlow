package com.jflow.core.event.command;

import java.util.Map;

import com.jflow.core.event.FlowEvent;

/**
 * 推进当前FlowInstance
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class ResumeCommand implements FlowEvent<ResumeCommand> {
    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public ResumeCommand getPayload() {
        return this;
    }
}
