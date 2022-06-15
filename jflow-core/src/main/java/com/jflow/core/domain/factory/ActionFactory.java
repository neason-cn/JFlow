package com.jflow.core.domain.factory;

import cn.hutool.core.util.IdUtil;
import com.jflow.core.engine.ctx.ScriptContext;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.service.impl.ScriptServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class ActionFactory {

    private final ScriptServiceImpl parser;

    public AbstractAction create(ActionSpec spec, ScriptContext context) {
        Class<? extends AbstractAction> clazz = spec.getActionType().getClazz();
        AbstractAction action = parser.replace(spec.getParams(), context).to(clazz);
        action.setActionInstanceId(IdUtil.simpleUUID());
        action.setActionSpec(spec);
        return action;
    }

}
