package com.jflow.core.domain.flow.reference.instance;

import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.flow.reference.spec.EdgeSpec;
import com.jflow.core.domain.graph.Edge;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeInstance implements Edge<AbstractNodeInstance> {

    /**
     * The spec of this instance.
     */
    private final EdgeSpec spec;

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
    private transient AbstractNodeInstance source;

    /**
     * The target node.
     */
    private transient AbstractNodeInstance target;

    @Override
    public String getEdgeId() {
        return this.spec.getEdgeId();
    }

    @Override
    public String getEdgeName() {
        return this.spec.getEdgeName();
    }
}
