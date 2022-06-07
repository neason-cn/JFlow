package com.jflow.core.engine.flow.spec;

import com.jflow.common.enums.Type;
import com.jflow.core.engine.enums.type.TaskTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * The specification of the task.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskSpec implements Type, Serializable {

    private static final long serialVersionUID = 2022001L;

    /**
     * The display name of this task.
     */
    private String taskName;

    /**
     * The task type.
     *
     * @see TaskTypeEnum
     */
    private TaskTypeEnum taskType;

    /**
     * The action which will be actioned only once.
     */
    private ActionSpec onExecute;

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

    @Override
    public String getType() {
        return this.taskType.getType();
    }

}
