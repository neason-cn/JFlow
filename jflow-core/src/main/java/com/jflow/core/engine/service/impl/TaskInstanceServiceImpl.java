package com.jflow.core.engine.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.factory.ActionFactory;
import com.jflow.core.domain.factory.TaskInstanceFactory;
import com.jflow.core.domain.repository.TaskInstanceRepository;
import com.jflow.core.engine.ctx.RuntimeContext;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.TaskSpec;
import com.jflow.core.engine.service.TaskInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class TaskInstanceServiceImpl implements TaskInstanceService {

    private final ActionFactory actionFactory;
    private final TaskInstanceFactory taskInstanceFactory;
    private final TaskInstanceRepository taskInstanceRepository;


    @Override
    public TaskInstance createAndSaveTask(TaskSpec spec, String flowInstanceId, String nodeId, JSONObject context) {
        TaskInstance instance = taskInstanceFactory.create(spec, flowInstanceId, nodeId, context);
        save(instance);
        return instance;
    }

    @Override
    public AbstractAction initAction(ActionSpec spec, RuntimeContext context) {
        if (null == spec) {
            return null;
        }
        return actionFactory.create(spec, context);
    }

    @Override
    public void save(TaskInstance taskInstance) {
        taskInstanceRepository.save(taskInstance);
    }
}
