package com.jflow.core.engine.service.impl;

import com.jflow.core.engine.service.AsyncRunner;
import com.jflow.infra.spi.cache.CacheSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class AsyncRunnerImpl implements AsyncRunner {

    private final CacheSpi cacheSpi;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void asyncRun(String key, Runnable runnable) {
        try {
            cacheSpi.tryLock(key, 3000);
            executor.submit(runnable);
        } finally {
            cacheSpi.unLock(key);
        }
    }

}
