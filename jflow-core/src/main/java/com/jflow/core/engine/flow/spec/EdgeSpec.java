package com.jflow.core.engine.flow.spec;

import com.alibaba.fastjson.annotation.JSONField;
import com.jflow.core.engine.graph.Edge;
import com.jflow.infra.spi.script.type.BooleanScript;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * The specification of the edge.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeSpec implements Edge<NodeSpec>, Serializable {


    private static final long serialVersionUID = 2022001L;

    /**
     * Edge id
     */
    private String edgeId;

    /**
     * Edge name
     */
    private String edgeName;

    /**
     * The script of the edge.
     * The script must return a Boolean value, which as a symbol of whether the
     * target node will be fire or not.
     */
    private BooleanScript script;

    /**
     * The nodeId of source(for deSerialize).
     */
    private String sourceNodeId;

    /**
     * The nodeId of target(for deSerialize).
     */
    private String targetNodeId;

    /**
     * Transient for avoid circular reference when serialize this.
     * Source node.
     */
    @JSONField(serialize = false)
    private transient NodeSpec source;

    /**
     * Transient for avoid circular reference when serialize this.
     * Target node.
     */
    @JSONField(serialize = false)
    private transient NodeSpec target;

    public boolean hasScript() {
        return null != script && StringUtils.isNotBlank(script.getContent());
    }

    @Override
    public String toString() {
        return "EdgeSpec{" +
                "edgeId='" + edgeId + '\'' +
                ", edgeName='" + edgeName + '\'' +
                ", script=" + script +
                ", sourceNodeId='" + sourceNodeId + '\'' +
                ", targetNodeId='" + targetNodeId + '\'' +
                '}';
    }
}
