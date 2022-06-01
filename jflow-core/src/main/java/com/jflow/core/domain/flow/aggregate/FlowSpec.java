package com.jflow.core.domain.flow.aggregate;

import com.jflow.core.domain.flow.reference.spec.EdgeSpec;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import com.jflow.core.domain.graph.Graph;
import lombok.Data;

import java.util.Set;

/**
 * The
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowSpec implements Graph<NodeSpec, EdgeSpec> {

    /**
     * All node spec.
     */
    private transient Set<NodeSpec> nodes;

    /**
     * All edge spec.
     */
    private transient Set<EdgeSpec> edges;

}
