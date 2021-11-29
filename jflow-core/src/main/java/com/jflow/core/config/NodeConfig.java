package com.jflow.core.config;

import java.io.Serializable;

/**
 * 节点的系统配置信息
 *
 * @author : neason-cn
 * @date : 2021/11/29
 */
public interface NodeConfig extends Serializable {

    /**
     * 当前节点是否自动执行
     *
     * @return boolean
     */
    Boolean autoResume();

    /**
     * 是否异步执行
     *
     * @return boolean
     */
    Boolean isAsync();

    /**
     * 当前节点能否被跳过
     *
     * @return boolean
     */
    Boolean canSkip();

    /**
     * 当前节点能否重试
     *
     * @return boolean
     */
    Boolean canRetry();

    /**
     * 重试次数
     *
     * @return int
     */
    Integer getRetryCount();

    /**
     * 设置配置kv
     *
     * @param key key
     * @param value value
     */
    void set(String key, String value);

}
