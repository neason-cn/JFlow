package com.jflow.core.domain.factory;

import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.TaskSpec;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@Component
public class TaskInstanceFactory {

    public TaskInstance create(TaskSpec spec, String flowInstanceId, String nodeId) {
        return null;
    }

}
