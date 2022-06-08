package com.jflow.core.engine.flow.instance.node;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.ctx.Callback;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.ctx.RuntimeContext;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.service.TaskInstanceService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.jflow.common.error.Errors.UNSUPPORTED_NODE_OPERATION_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StartNode extends AbstractNodeInstance {
    public static final String NODE_ID = "START";

    @Override
    public void onSignal(Context ctx, EdgeInstance trigger) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, NODE_ID, "onSignal");
    }

    @Override
    public void onFire(Context ctx, JSONObject args) {
        ActionSpec startActionSpec = ctx.getFlowInstance().getSpec().getOnStart();
        TaskInstanceService service = ctx.getRuntime().getTaskInstanceService();
        AbstractAction action = service.initAction(startActionSpec, new RuntimeContext(args, new JSONObject()));
        action.onExecute(ctx);
        fireOutgoingEdges(ctx);
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
