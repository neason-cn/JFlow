package com.jflow.core.domain.flow.reference.instance.action;

import com.jflow.common.enums.Type;
import com.jflow.core.domain.engine.activity.ActionActivity;
import com.jflow.core.domain.flow.reference.spec.action.ActionSpec;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractAction implements ActionActivity, Type {
    private ActionSpec actionSpec;

    @Override
    public String getType() {
        return this.actionSpec.getType();
    }
}
