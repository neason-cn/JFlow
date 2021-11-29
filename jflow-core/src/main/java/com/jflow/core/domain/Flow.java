package com.jflow.core.domain;

import java.io.Serializable;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public interface Flow extends Serializable {

    /**
     * 流程的名称
     *
     * @return String
     */
    String getFlowName();

    /**
     * 流程的唯一ID
     *
     * @return String
     */
    String getFlowId();

    /**
     * 获取流程运行的拓扑图
     *
     * @return String
     */
    String getUiInfo();

}
