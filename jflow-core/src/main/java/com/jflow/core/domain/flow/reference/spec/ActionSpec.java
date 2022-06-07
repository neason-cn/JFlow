package com.jflow.core.domain.flow.reference.spec;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.core.domain.enums.type.ActionTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class ActionSpec implements Type, Serializable {

    private static final long serialVersionUID = 2022001L;

    /**
     * The action type.
     *
     * @see ActionTypeEnum
     */
    private ActionTypeEnum actionType;

    private JSONObject params;

    @Override
    public String getType() {
        return this.actionType.getType();
    }

}
