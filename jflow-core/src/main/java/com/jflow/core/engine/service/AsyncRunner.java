package com.jflow.core.engine.service;

/**
 * @author neason
 * @since 0.0.1
 */
public interface AsyncRunner {
    void asyncRun(String key, Runnable runnable);
}
