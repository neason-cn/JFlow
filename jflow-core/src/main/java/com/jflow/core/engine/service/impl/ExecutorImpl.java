package com.jflow.core.engine.service.impl;

import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.service.Executor;
import com.jflow.infra.spi.cache.CacheSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.jflow.common.error.Errors.LOCK_KEY_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ExecutorImpl implements Executor {

    @Value("${jflow.executor.async:false}")
    private boolean executorMode;

    @Value("${jflow.executor.pool.size:100}")
    private int executorPoolSize;
    private final CacheSpi cacheSpi;
    private ExecutorService executor;

    @PostConstruct
    public void init() {
        log.info("the mode of Executor is {} from {}", executorMode, "jflow.executor.async");
        log.info("the pool size of Executor is {} from {}", executorPoolSize, "jflow.executor.pool.size");
        executor = Executors.newFixedThreadPool(executorPoolSize);
    }

    @Override
    public void asyncRun(String key, Runnable runnable) {
        boolean locked = false;

        try {
            locked = cacheSpi.tryLock(key, 3000);

            if (!locked) {
                throw new FlowException(LOCK_KEY_ERROR, key);
            }

            if (executorMode) {
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
