package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowSpecConvertor {

    public FlowSpec fetch(FlowSpecVO newInfo, FlowSpec oldSpec) {
        return new FlowSpec();
    }

}
