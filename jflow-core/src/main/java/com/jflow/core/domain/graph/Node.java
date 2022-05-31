package com.jflow.core.domain.graph;

import java.util.Set;

/**
 * The node of a graph, which connect with multiple edges.
 *
 * @author neason
 * @since 0.0.1
 */
public interface Node {

    /**
     * @return the unique id of this node in a graph.
     */
    String nodeId();

    /**
     * @return the display name of this node.
     */
    String nodeName();

    /**
     * @return the edges which point to this node.
     */
    Set<Edge> incoming();

    /**
     * @param edges incoming edges
     */
    void setIncoming(Set<Edge> edges);

    /**
     * @return the edges which diffuse from this node.
     */
    Set<Edge> outgoing();

    /**
     * @param edges outgoing edges
     */
    void setOutgoing(Set<Edge> edges);

}
