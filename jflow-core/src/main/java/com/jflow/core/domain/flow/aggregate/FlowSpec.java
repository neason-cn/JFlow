package com.jflow.core.domain.flow.aggregate;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.enums.status.FlowSpecStatusEnum;
import com.jflow.core.domain.flow.reference.spec.EdgeSpec;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import com.jflow.core.domain.flow.reference.spec.action.AbstractActionSpec;
import com.jflow.core.domain.graph.Graph;
import com.jflow.infra.spi.script.type.JsonScript;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * The static spec of flow, the most important info is the nodes and edges.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowSpec implements Graph<NodeSpec, EdgeSpec> {

    /**
     * The unique id of this spec version.
     */
    private String flowSpecId;

    /**
     * The code of this spec version, the specs those have the same code are regarded as the same spec.
     */
    private String flowSpecCode;

    /**
     * The version of this spec version.
     */
    private int flowSpecVersion;

    /**
     * The description of this spec version.
     */
    private String description;

    /**
     * The status of this spec version.
     */
    private FlowSpecStatusEnum status;

    /**
     * Can have multi flow instance created by using this spec version or not.
     */
    private boolean enableMultiInstance;

    /**
     * The static context data which will be merged into the flow instance when create the instance immediately.
     */
    private JSONObject initContext;

    /**
     * The result of script will be regarded as the result of a flow instance.
     * The script will be executed when a flow instance went to the final status.
     */
    private JsonScript result;

    /**
     * Scheduled to create a flow instance by using the spec version.
     */
    private boolean scheduled;

    /**
     * The cron expression.
     */
    private String cron;

    /**
     * The user who create this spec version.
     */
    private FlowUser createBy;

    /**
     * The latest user who release the spec version
     */
    private FlowUser releaseBy;

    /**
     * The time when create this spec version.
     */
    private Date createAt;

    /**
     * The latest user who release the spec version
     */
    private Date releaseAt;

    /**
     * The tenant of this spec version.
     */
    private String tenant;

    /**
     * The action will be actioned when create a flow instance by using this spec version.
     */
    private AbstractActionSpec onStart;

    /**
     * The action will be actioned when a flow instance went to final status which created by using this spec version.
     */
    private AbstractActionSpec onEnd;

    /**
     * All node spec.
     */
    private transient Set<NodeSpec> nodes;

    /**
     * All edge spec.
     */
    private transient Set<EdgeSpec> edges;

}
