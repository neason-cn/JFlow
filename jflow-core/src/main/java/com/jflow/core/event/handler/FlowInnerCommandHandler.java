package com.jflow.core.event.handler;

import com.jflow.core.event.FlowEventHandler;
import com.jflow.core.event.command.FlowInnerCommand;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public abstract class FlowInnerCommandHandler<T extends FlowInnerCommand<?>> implements FlowEventHandler<T> {

    @Override
    public Object onReceive(T flowEvent) {
        return null;
    }

    @Override
    public void onSuccess(T flowEvent) {

    }

    @Override
    public void onError(T flowEvent) {

    }
}
