package com.jflow.core.domain;

import java.io.Serializable;

import com.jflow.core.context.FlowInstanceContext;
import com.jflow.core.node.FlowNode;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public interface FlowInstance extends Serializable {

    /**
     * 流程实例的模板
     *
     * @return Flow
     */
    Flow getFlow();

    /**
     * 流程实例的上下文
     *
     * @return FlowInstanceContext
     */
    FlowInstanceContext getContext();

    /**
     * 流程实例当前操作的节点
     *
     * @return FlowNode
     */
    FlowNode getCurrentNode();

    /**
     * 当前流程实例的父实例（流程可嵌套）
     *
     * @return FlowInstance
     */
    FlowInstance getParentInstance();

}
