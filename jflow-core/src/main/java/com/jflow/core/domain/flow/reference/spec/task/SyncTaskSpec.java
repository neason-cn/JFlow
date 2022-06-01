package com.jflow.core.domain.flow.reference.spec.task;

import com.jflow.core.domain.flow.reference.spec.action.AbstractActionSpec;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class SyncTaskSpec extends AbstractTaskSpec {

    private static final long serialVersionUID = 2022001L;

    /**
     * The action which will be actioned only once.
     */
    private AbstractActionSpec onExecute;

}
