package com.jflow.core.engine.flow.instance.node;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.activity.NodeActivity;
import com.jflow.core.engine.ctx.Callback;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.ctx.ScriptContext;
import com.jflow.core.engine.enums.status.NodeInstanceStatusEnum;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.NodeSpec;
import com.jflow.core.engine.graph.Node;
import com.jflow.core.engine.service.TaskInstanceService;
import lombok.Data;

import java.util.Date;
import java.util.Set;

import static com.jflow.common.error.Errors.UNSUPPORTED_NODE_OPERATION_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractNodeInstance implements Type, Node<EdgeInstance>, NodeActivity {

    private NodeSpec spec;
    private NodeInstanceStatusEnum status;
    private TaskInstance latestTask;
    @JSONField(serialize = false)
    private transient Set<EdgeInstance> incoming;
    @JSONField(serialize = false)
    private transient Set<EdgeInstance> outgoing;
    private Boolean waitAll;
    private Boolean autoFire;
    private Boolean autoSkip;
    private Boolean enableSkip;
    private Boolean enableRetry;
    private Boolean interruptWhenSubmitFailed;
    private Boolean interruptWhenExecuteFailed;
    private Date firstSignalTime;
    private Date fireTime;
    private Date finishTime;

    @Override
    public String getType() {
        return this.spec.getNodeType().getType();
    }

    @Override
    public String getNodeId() {
        return this.spec.getNodeId();
    }

    @Override

    public String getNodeName() {
        return this.spec.getNodeName();
    }

    protected void fireOutgoingEdges(Context ctx) {
        getOutgoing().forEach(edge -> {
            edge.onFire(ctx);
        });
    }

    protected void runAndIgnoreResult(Context ctx, ActionSpec spec) {
        if (null == spec) {
            return;
        }
        TaskInstanceService instanceService = ctx.getRuntime().getTaskInstanceService();
        JSONObject taskContext = this.latestTask == null ? new JSONObject() : this.latestTask.getTaskContext();;
        AbstractAction action = instanceService.initAction(spec,
                new ScriptContext(ctx.getFlowInstance().getContext(), taskContext));
        action.onExecute(ctx);
    }

    @Override
    public void onSignal(Context ctx, EdgeInstance trigger) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, "onSignal");
    }

    @Override
    public void onFire(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, "onFire");
    }

    @Override
    public void onSkip(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, "onSkip");
    }

    @Override
    public void onRetry(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, "onRetry");
    }

    @Override
    public void onCancel(Context ctx, JSONObject args) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, "onCancel");
    }

    @Override
    public void onCallback(Context ctx, Callback callback) {
        throw new FlowException(UNSUPPORTED_NODE_OPERATION_ERROR, "onCallback");
    }

    @Override
    public String toString() {
        return "AbstractNodeInstance{" +
                "spec=" + spec +
                ", status=" + status +
                ", latestTask=" + latestTask +
                ", waitAll=" + waitAll +
                ", autoFire=" + autoFire +
                ", autoSkip=" + autoSkip +
                ", enableSkip=" + enableSkip +
                ", enableRetry=" + enableRetry +
                ", interruptWhenSubmitFailed=" + interruptWhenSubmitFailed +
                ", interruptWhenExecuteFailed=" + interruptWhenExecuteFailed +
                ", firstSignalTime=" + firstSignalTime +
                ", fireTime=" + fireTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
