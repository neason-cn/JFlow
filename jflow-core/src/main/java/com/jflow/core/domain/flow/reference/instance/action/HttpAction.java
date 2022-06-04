package com.jflow.core.domain.flow.reference.instance.action;

import com.jflow.core.domain.engine.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HttpAction extends AbstractAction {

    @Override
    public void onExecute(Context ctx) {

    }

}
