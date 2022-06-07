package com.jflow.core.domain.factory;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.spec.ActionSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ActionFactory {

    public AbstractAction create(ActionSpec spec, JSONObject context) {
        // todo place holder
        Class<? extends AbstractAction> clazz = spec.getActionType().getClazz();
        AbstractAction action = spec.getParams().to(clazz);
        action.setActionSpec(spec);
        return action;
    }

}
