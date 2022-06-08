package com.jflow.core.engine.flow.instance.node;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.ctx.Callback;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;

import static com.jflow.common.error.Errors.UNSUPPORTED_NODE_OPERATION_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
public class EndNode extends AbstractNodeInstance {
    public static final String NODE_ID = "END";

    @Override
    public void onSignal(Context ctx, EdgeInstance trigger) {
        ActionSpec endActionSpec = ctx.getFlowInstance().getSpec().getOnEnd();
        runAction(ctx, endActionSpec);
        ctx.getFlowInstance().setStatus(FlowInstanceStatusEnum.SUCCESS);
    }

    @Override
    public void onFire(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, NODE_ID, "onFire");
    }

    @Override
    public void onSkip(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, NODE_ID, "onSkip");
    }

    @Override
    public void onRetry(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, NODE_ID, "onRetry");
    }

    @Override
    public void onCancel(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, NODE_ID, "onCancel");
    }

    @Override
    public void onCallback(Context ctx, Callback callback) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, NODE_ID, "onCallback");
    }
}
