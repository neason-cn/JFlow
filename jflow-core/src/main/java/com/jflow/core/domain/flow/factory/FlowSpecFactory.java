package com.jflow.core.domain.flow.factory;

import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowSpecFactory {

    public FlowSpec create(FlowSpecVO vo, int nextVersion, String userId) {
        return new FlowSpec();
    }

}
