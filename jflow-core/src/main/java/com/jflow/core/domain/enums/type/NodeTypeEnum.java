package com.jflow.core.domain.enums.type;

import com.jflow.common.enums.Type;
import com.jflow.core.domain.flow.reference.instance.node.AbstractNodeInstance;
import com.jflow.core.domain.flow.reference.instance.node.EndNode;
import com.jflow.core.domain.flow.reference.instance.node.StartNode;
import com.jflow.core.domain.flow.reference.instance.node.TaskNode;
import lombok.Getter;

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

}
