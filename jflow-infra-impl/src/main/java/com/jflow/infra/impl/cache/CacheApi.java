package com.jflow.infra.impl.cache;

import com.jflow.infra.spi.cache.CacheSpi;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class CacheApi implements CacheSpi {
    @Override
    public Serializable get(Serializable key) {
        return null;
    }

    @Override
    public Serializable put(Serializable key, Serializable value) {
        return null;
    }

    @Override
    public Serializable put(Serializable key, Serializable value, long expireTime) {
        return null;
    }

    @Override
    public Serializable delete(Serializable key) {
        return null;
    }

    @Override
    public boolean tryLock(Serializable key, long expireTime) {
        return false;
    }

    @Override
    public boolean unLock(Serializable key) {
        return false;
    }
}
