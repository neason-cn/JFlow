package com.jflow.core.event.dispatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jflow.core.constants.FlowEventHeaders;
import com.jflow.core.event.FlowEvent;
import com.jflow.core.event.FlowEventHandler;
import com.jflow.core.event.command.FlowInnerCommand;
import com.jflow.core.event.handler.FlowInnerCommandHandler;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class FlowEventDispatcher {

    private final Map<String, FlowInnerCommandHandler<?>> innerHandlerMap = new ConcurrentHashMap<>(16);
    private final Map<String, FlowEventHandler<?>> bizHandlerMap = new ConcurrentHashMap<>(16);

    /**
     * 分发FlowEvent，根据Event类型选择相应的FlowEventHandler
     *
     * @param flowEvent FlowEvent
     * @return FlowEventHandler
     */
    FlowEventHandler<?> dispatch(FlowEvent<?> flowEvent) {
        if (flowEvent instanceof FlowInnerCommand) {
            FlowInnerCommand<?> command = (FlowInnerCommand<?>)flowEvent;
            return innerHandlerMap.get(command.getCommandType().getType());
        } else {
            String bizEventType = flowEvent.getHeaders().get(FlowEventHeaders.COMMAND_TYPE);
            return bizHandlerMap.get(bizEventType);
        }
    }

    /**
     * 注册系统内部的Handler
     *
     * @param handlerKey unique key
     * @param handler    FlowInnerCommandHandler
     */
    public void registerInner(String handlerKey, FlowInnerCommandHandler<?> handler) {
        this.innerHandlerMap.put(handlerKey, handler);
    }

    /**
     * 注册业务/扩展的Handler
     *
     * @param handlerKey unique key
     * @param handler    FlowEventHandler
     */
    public void registerBiz(String handlerKey, FlowEventHandler<?> handler) {
        this.bizHandlerMap.put(handlerKey, handler);
    }
}
