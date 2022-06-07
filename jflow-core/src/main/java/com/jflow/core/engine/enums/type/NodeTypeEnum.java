package com.jflow.core.engine.enums.type;

import com.jflow.common.enums.Type;
import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.flow.instance.node.EndNode;
import com.jflow.core.engine.flow.instance.node.StartNode;
import com.jflow.core.engine.flow.instance.node.TaskNode;
import lombok.Getter;

import static com.jflow.common.error.Errors.NODE_INSTANCE_CLASS_NEW_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
public enum NodeTypeEnum implements Type {

    START("START", StartNode.class),

    TASK("TASK", TaskNode.class),

    END("END", EndNode.class);

    @Getter
    private final String type;
    @Getter
    private final Class<? extends AbstractNodeInstance> clazz;

    NodeTypeEnum(String type, Class<? extends AbstractNodeInstance> clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public static NodeTypeEnum of(String type) {
        for (NodeTypeEnum value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(NodeTypeEnum.class, type);
    }

    public AbstractNodeInstance newNode() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new FlowException(NODE_INSTANCE_CLASS_NEW_ERROR, clazz.getName());
        }
    }

}
