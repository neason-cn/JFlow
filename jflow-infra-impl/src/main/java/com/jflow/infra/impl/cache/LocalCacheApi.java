package com.jflow.infra.impl.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.jflow.infra.spi.cache.CacheSpi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class LocalCacheApi implements CacheSpi {

    @Value("${local.cache.capacity}")
    private Integer cacheCapacity;
    private Cache<Serializable, Serializable> cache;

    @PostConstruct
    private void init() {
        cache = CacheUtil.newFIFOCache(cacheCapacity);
    }

    @Override
    public Serializable get(Serializable key) {
        return cache.get(key);
    }

    @Override
    public void put(Serializable key, Serializable value) {
        cache.put(key, value);
    }

    @Override
    public void put(Serializable key, Serializable value, long expireTime) {
        cache.put(key, value, expireTime);
    }

    @Override
    public void delete(Serializable key) {
        cache.remove(key);
    }

    @Override
    public boolean tryLock(Serializable key, long expireTime) {
        if (null != cache.get(key)) {
            return false;
        }
        cache.put(key, Thread.currentThread().getName());
        return true;
    }

    @Override
    public boolean unLock(Serializable key) {
        Serializable value = cache.get(key);
        if (null == value) {
            return true;
        }

        if (!Thread.currentThread().getName().equals(value.toString())) {
            return false;
        }

        cache.remove(key);
        return true;
    }
}
