package com.jflow.infra.spi.cache;

import java.io.Serializable;

/**
 * @author neason
 * @since 0.0.1
 */
public interface CacheSpi {

    Serializable get(Serializable key);

    void put(Serializable key, Serializable value);

    void put(Serializable key, Serializable value, long expireTime);

    void delete(Serializable key);

    boolean tryLock(Serializable key, long expireTime);

    boolean unLock(Serializable key);

}
