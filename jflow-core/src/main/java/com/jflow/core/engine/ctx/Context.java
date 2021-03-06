package com.jflow.core.engine.ctx;

import com.jflow.core.engine.flow.aggregate.FlowInstance;
import lombok.Data;

/**
 * The context through all life cycle of the flow instance.
 * todo: remove Context.class runtime -> static spring context holder; flowInstance -> constructor of node/edge.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class Context {
    private Runtime runtime;
    private FlowInstance flowInstance;

    public static Context init(Runtime runtime, FlowInstance flowInstance) {
        Context context = new Context();
        context.setRuntime(runtime);
        context.setFlowInstance(flowInstance);
        return context;
    }
}
