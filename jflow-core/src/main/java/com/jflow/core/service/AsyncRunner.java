package com.jflow.core.service;

/**
 * @author neason
 * @since 0.0.1
 */
public interface AsyncRunner {
    void asyncRun(String key, Runnable runnable);
}
