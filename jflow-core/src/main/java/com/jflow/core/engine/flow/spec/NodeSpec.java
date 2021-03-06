package com.jflow.core.engine.flow.spec;

import com.alibaba.fastjson.annotation.JSONField;
import com.jflow.core.engine.enums.type.NodeTypeEnum;
import com.jflow.core.engine.graph.Node;
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
     * The node type.
     */
    private NodeTypeEnum nodeType;

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
     * The script which return a Boolean value as a symbol of can skip this node manually.
     */
    private BooleanScript enableSkip;

    /**
     * The script which return a Boolean value as a symbol of can retry this node when it executes failed manually.
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
     * The action before fire task.
     */
    private Set<ActionSpec> preActions;

    /**
     * The task will be run in this node.
     */
    private TaskSpec taskSpec;

    /**
     * The action after finish task.
     */
    private Set<ActionSpec> postActions;

    /**
     * Transient for avoid circular reference when serialize this.
     * The incoming edges.
     */
    @JSONField(serialize = false)
    private transient Set<EdgeSpec> incoming;

    /**
     * Transient for avoid circular reference when serialize this.
     * The outgoing edges.
     */
    @JSONField(serialize = false)
    private transient Set<EdgeSpec> outgoing;

    @Override
    public String toString() {
        return "NodeSpec{" +
                "nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", waitAll=" + waitAll +
                ", autoFire=" + autoFire +
                ", autoSkip=" + autoSkip +
                ", enableSkip=" + enableSkip +
                ", enableRetry=" + enableRetry +
                ", interruptWhenSubmitFailed=" + interruptWhenSubmitFailed +
                ", interruptWhenExecuteFailed=" + interruptWhenExecuteFailed +
                ", labels=" + labels +
                ", before=" + preActions +
                ", taskSpec=" + taskSpec +
                ", after=" + postActions +
                ", incoming=" + incoming +
                ", outgoing=" + outgoing +
                '}';
    }
}
