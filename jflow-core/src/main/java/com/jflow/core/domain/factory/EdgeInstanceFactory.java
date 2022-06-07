package com.jflow.core.domain.factory;

import com.jflow.core.engine.enums.status.EdgeInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.EdgeSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class EdgeInstanceFactory {

    public EdgeInstance create(EdgeSpec edgeSpec) {
        EdgeInstance instance = new EdgeInstance(edgeSpec);
        instance.setStatus(EdgeInstanceStatusEnum.INIT);
        instance.setSourceNodeId(edgeSpec.getSourceNodeId());
        instance.setTargetNodeId(edgeSpec.getTargetNodeId());
        return instance;
    }

}
