package com.jflow.core.domain.flow.reference.spec;

import com.jflow.core.domain.graph.Node;
import com.jflow.infra.spi.script.type.BooleanScript;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class NodeSpec implements Node<EdgeSpec>, Serializable {

    private static final long serialVersionUID = 2022001L;

    /**
     * The node id.
     */
    private String nodeId;

    /**
     * The node name.
     */
    private String nodeName;

    /**
     * The script which return a Boolean value as a symbol of 'WaitMode'.
     * True  -> 'WaitAll': the node will wait(not fire) util all of {@link NodeSpec#incoming} comes and all success.
     * False -> 'WaitAny': the node will fire when any of {@link NodeSpec#incoming} comes and success, and ignore other edge.
     */
    private BooleanScript waitAll;

    /**
     * The script which return a Boolean value as a symbol of whether auto fire this node when 'WaitMode' matches.
     */
    private BooleanScript autoFire;

    /**
     * The script which return a Boolean value as a symbol of whether auto skip this node when 'WaitMode' matches.
     */
    private BooleanScript autoSkip;

    /**
     * The script which return a Boolean value as a symbol of user can skip this node manually.
     */
    private BooleanScript enableSkip;

    /**
     * The script which return a Boolean value as a symbol of user can retry this node when it executes failed manually.
     */
    private BooleanScript enableRetry;

    /**
     * The script which return a Boolean value as a symbol of whether interrupt the whole FlowInstance when this node
     * submits failed or not.
     */
    private BooleanScript interruptWhenSubmitFailed;

    /**
     * The script which return a Boolean value as a symbol of whether interrupt the whole FlowInstance when this node
     * executes failed or not.
     */
    private BooleanScript interruptWhenExecuteFailed;

    /**
     * The extended properties/labels which can add customize tag.
     */
    private Map<String, String> labels;

    /**
     * Transient for avoid circular reference when serialize this.
     * The incoming edges.
     */
    private transient Set<EdgeSpec> incoming;

    /**
     * Transient for avoid circular reference when serialize this.
     * The outgoing edges.
     */
    private transient Set<EdgeSpec> outgoing;

}
