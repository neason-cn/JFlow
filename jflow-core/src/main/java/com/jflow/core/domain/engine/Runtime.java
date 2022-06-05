package com.jflow.core.domain.engine;

import com.jflow.core.service.FlowInstanceService;
import com.jflow.core.service.TaskInstanceService;
import com.jflow.infra.spi.cache.CacheSpi;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationContext;

/**
 * The ability providers, not all beans in applicationContext.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
@Builder
public class Runtime {
    //-------------       SPI       -------------
    private CacheSpi cacheSpi;
    private ScriptSpi scriptSpi;

    //-------------     SERVICE     -------------
    private FlowInstanceService flowInstanceService;
    private TaskInstanceService taskInstanceService;

    //-------------  SpringContext  -------------
    private ApplicationContext applicationContext;
}
