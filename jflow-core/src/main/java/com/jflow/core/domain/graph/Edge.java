package com.jflow.core.domain.graph;

/**
 * The edge of a graph which connect two nodes.
 *
 * @author neason
 * @since 0.0.1
 */
public interface Edge {

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
    Node source();

    /**
     * @param source source node
     */
    void setSource(Node source);

    /**
     * @return the node which this edge point to.
     */
    Node target();

    /**
     * @param target target node
     */
    void setTarget(Node target);

    /**
     * the id of {@link Edge#source()}
     */
    String sourceNodeId();

    /**
     * the id of {@link Edge#target()}
     */
    String targetNodeId();

}
