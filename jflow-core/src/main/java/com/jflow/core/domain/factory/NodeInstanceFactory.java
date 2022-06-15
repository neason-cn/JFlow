package com.jflow.core.domain.factory;

import com.jflow.core.engine.enums.status.NodeInstanceStatusEnum;
import com.jflow.core.engine.enums.type.NodeTypeEnum;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.flow.spec.NodeSpec;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class NodeInstanceFactory {

    AbstractNodeInstance create(NodeSpec spec) {
        if (null == spec) {
            return null;
        }
        NodeTypeEnum nodeType = spec.getNodeType();
        AbstractNodeInstance instance = nodeType.newNode();
        instance.setNodeId(spec.getNodeId());
        instance.setSpec(spec);
        instance.setStatus(NodeInstanceStatusEnum.INIT);
        instance.setIncoming(new HashSet<>());
        instance.setOutgoing(new HashSet<>());
        return instance;
    }

}
