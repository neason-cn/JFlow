package com.jflow.core.task;

import com.jflow.core.domain.FlowInstance;

/**
 * @author : neason
 * @date : 2021/11/29
 */
public interface FlowTask<T> {

    /**
     * Task的唯一ID
     *
     * @return String
     */
    String getTaskId();

    /**
     * 执行一个具体的任务
     *
     * @param instance FlowInstance
     * @param taskParams T
     * @return Object
     */
    Object execute(FlowInstance instance,T taskParams);

}
