package com.jflow.core.engine.flow.aggregate;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.utils.JsonUtil;
import com.jflow.core.engine.activity.FlowActivity;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.graph.Graph;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.type.JsonScript;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowInstance implements Graph<AbstractNodeInstance, EdgeInstance>, FlowActivity {

    /**
     * The unique id of this flow instance.
     */
    private String flowInstanceId;

    /**
     * The task instance id which create this flow instance.
     */
    private String parentTaskInstanceId;

    /**
     * The static spec of the flow instance.
     */
    private final transient FlowSpec spec;

    /**
     * All edge instance.
     */
    private transient Set<EdgeInstance> edges;

    /**
     * All node instance.
     */
    private transient Set<AbstractNodeInstance> nodes;

    /**
     * The status of this flow instance.
     */
    private FlowInstanceStatusEnum status;

    /**
     * The data which create a flow instance.
     */
    private JSONObject input;

    /**
     * The result data of a flow instance.
     */
    private JSONObject output;

    /**
     * The global context which can be used in every node, edge, task and action.
     */
    private JSONObject context;

    /**
     * The time when create this flow instance.
     */
    private Date createAt;

    /**
     * The time when cancel this flow instance.
     */
    private Date cancelAt;

    @Override
    public void onTerminate(Context context) {

    }

    @Override
    public void onFinish(Context ctx) {
        this.output = resolveOutput(ctx);
        if (StringUtils.isNotBlank(this.parentTaskInstanceId)) {
            FlowInstanceResult result = FlowInstanceResult.builder()
                    .flowInstanceId(this.getFlowInstanceId())
                    .status(this.getStatus())
                    .output(this.getOutput())
                    .build();
            // complete the task if this flow instance is a sub_flow.
            ctx.getRuntime().getFlowInstanceService().completeTask(this.parentTaskInstanceId, JsonUtil.toJson(result));
        }
    }

    private JSONObject resolveOutput(Context ctx) {
        JsonScript outputScript = this.spec.getOutputScript();
        if (null != outputScript && StringUtils.isBlank(outputScript.getContent())) {
            ScriptResult<JSONObject> execute = ctx.getRuntime().getScriptSpi().execute(outputScript, this.getContext());
            if (execute.hasError()) {
                JSONObject json = new JSONObject();
                json.put("error", execute.getError());
                return json;
            } else {
                return execute.getResult();
            }
        }
        return new JSONObject();
    }

    public void mergeContext(JSONObject addition) {
        if (MapUtils.isEmpty(this.context)) {
            this.context = new JSONObject();
        }
        if (MapUtils.isNotEmpty(addition)) {
            this.context.putAll(addition);
        }
    }

    public Optional<AbstractNodeInstance> findNodeByTaskId(String taskInstanceId) {
        return this.getNodes().stream()
                .filter(node -> null != node.getLatestTask())
                .filter(node -> node.getLatestTask().getTaskInstanceId().equals(taskInstanceId))
                .findAny();
    }

    @Data
    @Builder
    public static class FlowInstanceResult {
        private String flowInstanceId;
        private FlowInstanceStatusEnum status;
        private JSONObject output;
    }

}
