package com.jflow.core.event.command;

import com.alibaba.fastjson.JSONObject;

import com.jflow.core.auth.FlowUser;
import com.jflow.core.enums.InnerCommandTypeEnum;
import com.jflow.core.event.command.StartCommand.StartCommandPayload;
import lombok.Data;

/**
 * 发起一条新的FlowInstance
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class StartCommand extends FlowInnerCommand<StartCommandPayload> {

    public StartCommand(JSONObject input) {
        super(input);
    }

    @Override
    protected StartCommandPayload extractPayload(JSONObject input) {
        return input.toJavaObject(StartCommandPayload.class);
    }

    @Override
    public InnerCommandTypeEnum getCommandType() {
        return InnerCommandTypeEnum.START;
    }

    @Data
    public static class StartCommandPayload {
        private String flowId;
        private FlowUser operator;
    }
}
