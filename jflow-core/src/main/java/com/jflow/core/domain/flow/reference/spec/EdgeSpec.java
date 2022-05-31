package com.jflow.core.domain.flow.reference.spec;

import com.jflow.core.domain.graph.Edge;
import lombok.Data;

/**
 * The specification of the edge.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeSpec implements Edge<NodeSpec> {

    private String edgeId;
    private String edgeName;
    private String sourceNodeId;
    private String targetNodeId;
    private transient NodeSpec source;
    private transient NodeSpec target;

}
