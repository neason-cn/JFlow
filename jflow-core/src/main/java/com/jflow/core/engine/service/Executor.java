package com.jflow.core.engine.service;

/**
 * @author neason
 * @since 0.0.1
 */
public interface Executor {
    void asyncRun(String key, Runnable runnable);
}
