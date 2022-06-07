package com.jflow.core.engine.flow.aggregate;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.activity.FlowActivity;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.graph.Graph;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;

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

    public void mergeContext(JSONObject addition) {
        if (MapUtils.isEmpty(this.context)) {
            this.context = new JSONObject();
        }
        this.context.putAll(addition);
    }

    public Optional<AbstractNodeInstance> findNodeByTaskId(String taskInstanceId) {
        return this.getNodes().stream()
                .filter(node -> null != node.getLatestTask())
                .filter(node -> node.getLatestTask().getTaskInstanceId().equals(taskInstanceId))
                .findAny();
    }

}
