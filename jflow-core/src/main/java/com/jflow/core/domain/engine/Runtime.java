package com.jflow.core.domain.engine;

import com.jflow.infra.spi.cache.CacheSpi;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.Data;

/**
 * The ability providers.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class Runtime {
    private CacheSpi cacheSpi;
    private ScriptSpi scriptSpi;
}
