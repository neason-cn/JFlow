package com.jflow.core.engine.service.impl;

import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.service.Executor;
import com.jflow.infra.spi.cache.CacheSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.jflow.common.error.Errors.LOCK_KEY_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class ExecutorImpl implements Executor {

    @Value("${flow.executor.async:false}")
    private boolean async;
    private final CacheSpi cacheSpi;
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void asyncRun(String key, Runnable runnable) {
        boolean locked = false;

        try {
            locked = cacheSpi.tryLock(key, 3000);

            if (!locked) {
                throw new FlowException(LOCK_KEY_ERROR, key);
            }

            if (async) {
                executor.submit(runnable);
                return;
            }

            runnable.run();
        } finally {
            if (locked) {
                cacheSpi.unLock(key);
            }
        }
    }

}
