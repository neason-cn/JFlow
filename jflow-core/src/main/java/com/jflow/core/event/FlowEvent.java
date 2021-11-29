package com.jflow.core.event;

import java.io.Serializable;
import java.util.Map;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public interface FlowEvent<T> extends Serializable {

    /**
     * FlowEvent的Header信息，携带部分协议和标识信息
     */
    Map<String, String> getHeaders();

    /**
     * FlowEvent真正的消息信息
     */
    T getPayload();

}
