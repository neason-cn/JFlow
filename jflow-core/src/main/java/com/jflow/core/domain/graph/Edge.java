package com.jflow.core.domain.graph;

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
    String edgeId();

    /**
     * @return the display name of this edge.
     */
    String edgeName();

    /**
     * @return the node which this edge diffuse from.
     */
    N source();

    /**
     * @param source source node
     */
    void setSource(N source);

    /**
     * @return the node which this edge point to.
     */
    N target();

    /**
     * @param target target node
     */
    void setTarget(N target);

    /**
     * the id of {@link Edge#source()}
     */
    String sourceNodeId();

    /**
     * the id of {@link Edge#target()}
     */
    String targetNodeId();

}
