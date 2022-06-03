package com.jflow.core.domain.engine;

import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.flow.aggregate.FlowInstance;
import lombok.Data;

/**
 * The context through all life cycle of the flow instance.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class Context {
    private FlowUser user;
    private Runtime runtime;
    private FlowInstance flowInstance;
}
