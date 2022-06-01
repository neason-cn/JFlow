package com.jflow.core.domain.flow.reference.spec.action;

import com.jflow.core.domain.enums.type.ActionTypeEnum;
import com.jflow.common.enums.Type;
import lombok.Data;

import java.io.Serializable;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractActionSpec implements Type, Serializable {

    private static final long serialVersionUID = 2022001L;

    /**
     * The action type.
     * @see ActionTypeEnum
     */
    private ActionTypeEnum actionType;

    @Override
    public String getType() {
        return this.actionType.getType();
    }
}
