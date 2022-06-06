package com.jflow.core.domain.flow.reference.instance.node;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.engine.Context;
import com.jflow.core.domain.engine.activity.NodeActivity;
import com.jflow.core.domain.enums.status.NodeInstanceStatusEnum;
import com.jflow.core.domain.flow.reference.instance.EdgeInstance;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.reference.action.AbstractAction;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import com.jflow.core.domain.graph.Node;
import com.jflow.core.service.TaskInstanceService;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractNodeInstance implements Node<EdgeInstance>, NodeActivity {

    private String flowInstanceId;
    private NodeSpec spec;
    private NodeInstanceStatusEnum status;
    private TaskInstance latestTask;
    private Set<EdgeInstance> incoming;
    private Set<EdgeInstance> outgoing;
    private boolean waitAll;
    private boolean autoFire;
    private boolean autoSkip;
    private boolean enableSkip;
    private boolean enableRetry;
    private boolean interruptWhenSubmitFailed;
    private boolean interruptWhenExecuteFailed;
    private Date firstSignalTime;
    private Date fireTime;
    private Date finishTime;

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

    protected void runAction(Context ctx, ActionSpec spec) {
        TaskInstanceService instanceService = ctx.getRuntime().getTaskInstanceService();
        AbstractAction action = instanceService.initAction(spec, ctx.getFlowInstance().getContext(), new JSONObject());
        action.onExecute(ctx);
    }

}
