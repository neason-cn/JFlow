package com.jflow.core.engine.flow.instance;

import com.alibaba.fastjson.annotation.JSONField;
import com.jflow.core.engine.activity.EdgeActivity;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.EdgeInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.flow.spec.EdgeSpec;
import com.jflow.core.engine.graph.Edge;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@Data
public class EdgeInstance implements Edge<AbstractNodeInstance>, EdgeActivity {

    /**
     * The spec of this instance.
     */
    private final EdgeSpec spec;

    /**
     * The status of an edge instance.
     */
    private EdgeInstanceStatusEnum status;

    /**
     * The error when running the script.
     */
    private String error;

    /**
     * The nodeId of source node.
     */
    private String sourceNodeId;

    /**
     * The nodeId of target node.
     */
    private String targetNodeId;

    /**
     * The source node.
     */
    @JSONField(serialize = false)
    private transient AbstractNodeInstance source;

    /**
     * The target node.
     */
    @JSONField(serialize = false)
    private transient AbstractNodeInstance target;

    @Override
    public String getEdgeId() {
        return this.spec.getEdgeId();
    }

    @Override
    public String getEdgeName() {
        return this.spec.getEdgeName();
    }

    @Override
    public void onFire(Context ctx) {
        // the default value is 'TRUE' when the script is empty.
        if (!this.spec.hasScript()) {
            this.error = null;
            this.status = EdgeInstanceStatusEnum.ALLOW;
            log.debug("the edge: {} has no script return true for default", this.getEdgeId());
            this.target.onSignal(ctx, this);
            return;
        }

        ScriptSpi scriptSpi = ctx.getRuntime().getScriptSpi();
        ScriptResult<Boolean> scriptResult = scriptSpi.execute(this.spec.getScript(), ctx.getFlowInstance().getContext());
        this.error = scriptResult.getError();

        // run script error
        if (scriptResult.hasError() || null == scriptResult.getResult()) {
            log.debug("script :{} run error: {}", this.getSpec().getScript().getContent(), scriptResult.getError());
            this.status = EdgeInstanceStatusEnum.ERROR;
            return;
        }

        // run script success but the result is 'FALSE'
        if (!scriptResult.getResult()) {
            this.status = EdgeInstanceStatusEnum.DENY;
            return;
        }

        // script result is 'TRUE'
        this.status = EdgeInstanceStatusEnum.ALLOW;
        this.target.onSignal(ctx, this);
    }

    @Override
    public String toString() {
        return "EdgeInstance{" +
                "spec=" + spec +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", sourceNodeId='" + sourceNodeId + '\'' +
                ", targetNodeId='" + targetNodeId + '\'' +
                '}';
    }
}
