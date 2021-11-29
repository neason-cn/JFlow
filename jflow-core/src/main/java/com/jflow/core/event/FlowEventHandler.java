package com.jflow.core.event;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public interface FlowEventHandler<E extends FlowEvent<?>> {

    /**
     * 接收并处理是事件
     *
     * @param flowEvent FlowEvent
     * @return Object
     */
    Object onReceive(E flowEvent);

    /**
     * 处理成功时调用
     *
     * @param flowEvent FlowEvent
     */
    void onSuccess(E flowEvent);

    /**
     * 处理异常时调用
     *
     * @param flowEvent FlowEvent
     */
    void onError(E flowEvent);

}
