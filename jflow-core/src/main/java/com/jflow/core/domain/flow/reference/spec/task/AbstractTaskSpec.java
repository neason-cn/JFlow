package com.jflow.core.domain.flow.reference.spec.task;

import com.jflow.core.domain.enums.TaskTypeEnum;
import com.jflow.core.domain.enums.Type;
import lombok.Data;

import java.io.Serializable;

/**
 * The specification of the task.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractTaskSpec implements Type, Serializable {

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

    @Override
    public String getType() {
        return this.taskType.getType();
    }
}
