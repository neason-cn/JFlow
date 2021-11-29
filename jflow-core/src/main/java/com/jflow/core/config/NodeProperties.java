package com.jflow.core.config;

import java.util.Properties;

/**
 * @author : neason-cn
 * @date : 2021/11/29
 */
public class NodeProperties extends Properties implements NodeConfig {

    private static final String ENABLE_AUTO_RESUME = "ENABLE_AUTO_RESUME";
    private static final String ENABLE_ASYNC = "ENABLE_ASYNC";
    private static final String ENABLE_SKIP = "ENABLE_SKIP";
    private static final String ENABLE_RETRY = "ENABLE_RETRY";
    private static final String RETRY_COUNT = "ENABLE_ASYNC";

    public NodeProperties() {
        super.setProperty(ENABLE_AUTO_RESUME, "false");
        super.setProperty(ENABLE_ASYNC, "false");
        super.setProperty(ENABLE_SKIP, "false");
        super.setProperty(ENABLE_RETRY, "false");
        super.setProperty(RETRY_COUNT, "0");
    }

    public NodeProperties(Properties config) {
        super(config);
    }

    @Override
    public Boolean autoResume() {
        return Boolean.valueOf(getProperty(ENABLE_AUTO_RESUME));
    }

    @Override
    public Boolean isAsync() {
        return Boolean.valueOf(getProperty(ENABLE_ASYNC));
    }

    @Override
    public Boolean canSkip() {
        return Boolean.valueOf(getProperty(ENABLE_SKIP));
    }

    @Override
    public Boolean canRetry() {
        return Boolean.valueOf(getProperty(ENABLE_RETRY));
    }

    @Override
    public Integer getRetryCount() {
        return Integer.valueOf(getProperty(RETRY_COUNT));
    }

    @Override
    public void set(String key, String value) {
        super.setProperty(key, value);
    }
}
