package com.jflow.core.domain.flow.factory;

import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.enums.status.NodeInstanceStatusEnum;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static com.jflow.common.error.Errors.NODE_INSTANCE_CLASS_NEW_ERROR;

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
        Class<? extends AbstractNodeInstance> clazz = spec.getNodeType().getClazz();
        AbstractNodeInstance instance = safeNew(clazz);
        instance.setSpec(spec);
        instance.setStatus(NodeInstanceStatusEnum.INIT);
        instance.setIncoming(new HashSet<>());
        instance.setOutgoing(new HashSet<>());
        return instance;
    }

    private AbstractNodeInstance safeNew(Class<? extends AbstractNodeInstance> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new FlowException(NODE_INSTANCE_CLASS_NEW_ERROR, clazz.getName());
        }
    }

}
