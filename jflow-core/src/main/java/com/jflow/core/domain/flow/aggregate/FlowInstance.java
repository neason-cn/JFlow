package com.jflow.core.domain.flow.aggregate;

import com.jflow.core.domain.flow.reference.instance.EdgeInstance;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.graph.Graph;
import lombok.Data;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowInstance implements Graph<AbstractNodeInstance, EdgeInstance> {

    private transient Set<AbstractNodeInstance> nodes;
    private transient Set<EdgeInstance> edges;

}
