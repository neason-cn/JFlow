package com.jflow.core.domain.flow.reference.spec;

import com.jflow.core.domain.graph.Node;
import lombok.Data;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class NodeSpec implements Node<EdgeSpec> {

    private String nodeId;
    private String nodeName;
    private transient Set<EdgeSpec> incoming;
    private transient Set<EdgeSpec> outgoing;

}
