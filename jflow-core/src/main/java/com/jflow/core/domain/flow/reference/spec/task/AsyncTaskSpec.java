package com.jflow.core.domain.flow.reference.spec.task;

import com.jflow.core.domain.flow.reference.spec.action.AbstractActionSpec;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class AsyncTaskSpec extends AbstractTaskSpec {

    private static final long serialVersionUID = 2022001L;

    /**
     * The action which will be actioned when fire firstly.
     */
    private AbstractActionSpec onSubmit;

    /**
     * The action which will be actioned all the time if the task is still running.
     */
    private AbstractActionSpec onQuery;

    /**
     * The action which will be actioned when cancel the task.
     */
    private AbstractActionSpec onCancel;

}
