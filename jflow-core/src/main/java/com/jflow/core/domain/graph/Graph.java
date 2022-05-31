package com.jflow.core.domain.graph;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A graph which contains nodes and edges.
 *
 * @author neason
 * @since 0.0.1
 */
public interface Graph {

    /**
     * connect nodes and edges, build reference of jvm object.
     *
     * @param nodes all nodes {@link Graph#nodes()}
     * @param edges all edges {@link Graph#edges()}
     */
    static void connect(Set<Node> nodes, Set<Edge> edges) {
        if (CollectionUtils.isEmpty(nodes) || CollectionUtils.isEmpty(edges)) {
            return;
        }

        Map<String, Node> nodeMap = nodes.stream()
                .collect(Collectors.toMap(Node::nodeId, Function.identity()));

        edges.forEach(edge -> {
            String sourceNodeId = edge.sourceNodeId();
            Node sourceNode = nodeMap.get(sourceNodeId);
            String targetNodeId = edge.targetNodeId();
            Node targetNode = nodeMap.get(targetNodeId);

            edge.setSource(sourceNode);
            edge.setTarget(targetNode);

            if (null != sourceNode) {
                if (CollectionUtils.isEmpty(sourceNode.outgoing())) {
                    sourceNode.setOutgoing(new HashSet<>(2));
                }
                sourceNode.outgoing().add(edge);
            }
            if (null != targetNode) {
                if (CollectionUtils.isEmpty(targetNode.incoming())) {
                    targetNode.setIncoming(new HashSet<>(2));
                }
                targetNode.incoming().add(edge);
            }
        });
    }

    /**
     * @return all nodes of a graph.
     */
    Set<Node> nodes();

    /**
     * @return all edges of a graph.
     */
    Set<Edge> edges();

    /**
     * find node by id.
     *
     * @param nodeId {@link Node#nodeId()}
     * @return Node
     */
    default Optional<Node> findNode(String nodeId) {
        if (StringUtils.isBlank(nodeId)) {
            return Optional.empty();
        }

        if (CollectionUtils.isEmpty(nodes())) {
            return Optional.empty();
        }

        return nodes().stream()
                .filter(node -> nodeId.equals(node.nodeId()))
                .findAny();
    }

    /**
     * find edge by id.
     *
     * @param edgeId {@link Edge#edgeId()}
     * @return Edge
     */
    default Optional<Edge> findEdge(String edgeId) {
        if (StringUtils.isBlank(edgeId)) {
            return Optional.empty();
        }

        if (CollectionUtils.isEmpty(edges())) {
            return Optional.empty();
        }

        return edges().stream()
                .filter(edge -> edgeId.equals(edge.edgeId()))
                .findAny();
    }

}
