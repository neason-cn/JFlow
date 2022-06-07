package com.jflow.core.engine.graph;

/**
 * The edge of a graph which connect two nodes.
 *
 * @param <N> the node type
 * @author neason
 * @since 0.0.1
 */
public interface Edge<N extends Node<?>> {

    /**
     * @return the unique id of this edge in a graph.
     */
    String getEdgeId();

    /**
     * @return the display name of this edge.
     */
    String getEdgeName();

    /**
     * @return the node which this edge diffuse from.
     */
    N getSource();

    /**
     * @param source source node
     */
    void setSource(N source);

    /**
     * @return the node which this edge point to.
     */
    N getTarget();

    /**
     * @param target target node
     */
    void setTarget(N target);

    /**
     * the id of {@link Edge#getSource()}
     */
    String getSourceNodeId();

    /**
     * the id of {@link Edge#getTarget()}
     */
    String getTargetNodeId();

}
