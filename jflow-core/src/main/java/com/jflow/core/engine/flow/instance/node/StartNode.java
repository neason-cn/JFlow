package com.jflow.core.engine.flow.instance.node;

import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StartNode extends AbstractNodeInstance {
    public static final String NODE_ID = "START";

    @Override
    public void onSignal(Context ctx, EdgeInstance edgeInstance) {
        ActionSpec endActionSpec = ctx.getFlowInstance().getSpec().getOnStart();
        runAndIgnoreResult(ctx, endActionSpec);
        fireOutgoingEdges(ctx);
    }

}
