package com.jflow.core.domain.flow.aggregate;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.domain.flow.reference.instance.EdgeInstance;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.graph.Graph;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowInstance implements Graph<AbstractNodeInstance, EdgeInstance> {

    /**
     * The unique id of this flow instance.
     */
    private String flowInstanceId;

    /**
     * The static spec of the flow instance.
     */
    private transient FlowSpec spec;

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
     * The user who create this flow instance.
     */
    private FlowUser createBy;

    /**
     * The user who cancel this flow instance.
     */
    private FlowUser cancelBy;

    /**
     * The time when create this flow instance.
     */
    private Date createAt;

    /**
     * The time when cancel this flow instance.
     */
    private Date cancelAt;

}
