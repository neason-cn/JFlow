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

    private final EdgeSpec spec;
    private String sourceNodeId;
    private String targetNodeId;
    private transient AbstractNodeInstance source;
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
