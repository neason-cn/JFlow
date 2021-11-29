package com.jflow.core.node;

import java.io.Serializable;

import com.jflow.core.config.NodeConfig;
import com.jflow.core.domain.FlowInstance;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public interface FlowNode extends Serializable {

    /**
     * 获取节点的配置信息
     *
     * @return NodeConfig
     */
    NodeConfig getNodeConfig();

    /**
     * 同步执行
     *
     * @param flowInstance FlowInstance
     * @return Object
     */
    Object invoke(FlowInstance flowInstance);

    /**
     * 异步执行
     *
     * @param flowInstance FlowInstance
     * @return Object
     */
    Object asyncInvoke(FlowInstance flowInstance);
}
