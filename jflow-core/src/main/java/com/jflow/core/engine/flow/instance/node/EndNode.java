package com.jflow.core.engine.flow.instance.node;

import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;

/**
 * @author neason
 * @since 0.0.1
 */
public class EndNode extends AbstractNodeInstance {

    @Override
    public void onSignal(Context ctx, EdgeInstance trigger) {
        ActionSpec endActionSpec = ctx.getFlowInstance().getSpec().getOnEnd();
        runAndIgnoreResult(ctx, endActionSpec);
        ctx.getFlowInstance().setStatus(FlowInstanceStatusEnum.SUCCESS);
    }

}
