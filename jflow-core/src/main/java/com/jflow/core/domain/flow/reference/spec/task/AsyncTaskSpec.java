package com.jflow.core.domain.flow.reference.spec.task;

import com.jflow.core.domain.flow.reference.spec.action.ActionSpec;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AsyncTaskSpec extends AbstractTaskSpec {

    private static final long serialVersionUID = 2022001L;

    /**
     * The action which will be actioned when fire firstly.
     */
    private ActionSpec onSubmit;

    /**
     * The action which will be actioned all the time if the task is still running.
     */
    private ActionSpec onQuery;

    /**
     * The action which will be actioned when cancel the task.
     */
    private ActionSpec onCancel;

}
