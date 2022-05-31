package com.jflow.core.domain.graph;

import java.util.Set;

/**
 * The node of a graph, which connect with multiple edges.
 *
 * @param <E> the edge type
 * @author neason
 * @since 0.0.1
 */
public interface Node<E extends Edge<?>> {

    /**
     * @return the unique id of this node in a graph.
     */
    String getNodeId();

    /**
     * @return the display name of this node.
     */
    String getNodeName();

    /**
     * @return the edges which point to this node.
     */
    Set<E> getIncoming();

    /**
     * @param edges incoming edges
     */
    void setIncoming(Set<E> edges);

    /**
     * @return the edges which diffuse from this node.
     */
    Set<E> getOutgoing();

    /**
     * @param edges outgoing edges
     */
    void setOutgoing(Set<E> edges);

}
