package com.jflow.core.domain.engine;

import com.jflow.core.service.FlowInstanceService;
import com.jflow.infra.spi.cache.CacheSpi;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.Builder;
import lombok.Data;

/**
 * The ability providers, not all beans in applicationContext.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
@Builder
public class Runtime {
    private CacheSpi cacheSpi;
    private ScriptSpi scriptSpi;
    private FlowInstanceService flowInstanceService;
}
