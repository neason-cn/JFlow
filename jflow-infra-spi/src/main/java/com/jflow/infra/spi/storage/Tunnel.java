package com.jflow.infra.spi.storage;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface Tunnel<T> {

    Optional<T> getById(String id);

    void save(T entity);

}
