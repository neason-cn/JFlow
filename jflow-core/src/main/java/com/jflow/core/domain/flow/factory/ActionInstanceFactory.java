package com.jflow.core.domain.flow.factory;

import com.jflow.core.domain.flow.reference.instance.action.AbstractActionInstance;
import com.jflow.core.domain.flow.reference.spec.action.ActionSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ActionInstanceFactory {

    AbstractActionInstance create(ActionSpec spec) {
        return null;
    }

}
