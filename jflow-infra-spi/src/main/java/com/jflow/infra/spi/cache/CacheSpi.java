package com.jflow.infra.spi.cache;

import java.io.Serializable;

/**
 * @author neason
 * @since 0.0.1
 */
public interface CacheSpi {

    Serializable get(Serializable key);

    Serializable put(Serializable key, Serializable value);

    Serializable put(Serializable key, Serializable value, long expireTime);

    Serializable delete(Serializable key);

    boolean tryLock(Serializable key, long expireTime);

    boolean unLock(Serializable key);

}
