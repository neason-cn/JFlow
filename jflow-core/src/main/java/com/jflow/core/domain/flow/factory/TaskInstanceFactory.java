package com.jflow.core.domain.flow.factory;

import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskInstanceFactory {

    public TaskInstance create(TaskSpec spec, String flowInstanceId, String nodeId) {
        return null;
    }

}
