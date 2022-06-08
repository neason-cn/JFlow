package com.jflow.core.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.factory.TaskInstanceFactory;
import com.jflow.core.domain.repository.TaskInstanceRepository;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.TaskSpec;
import com.jflow.core.service.TaskInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class TaskInstanceServiceImpl implements TaskInstanceService {

    private final TaskInstanceFactory taskInstanceFactory;
    private final TaskInstanceRepository taskInstanceRepository;


    @Override
    public TaskInstance createAndSaveTask(TaskSpec spec, String flowInstanceId, String nodeId, JSONObject context) {
        TaskInstance instance = taskInstanceFactory.create(spec, flowInstanceId, nodeId);
        save(instance);
        return instance;
    }

    @Override
    public AbstractAction initAction(ActionSpec spec, JSONObject flowContext, JSONObject taskContext) {
        return null;
    }

    @Override
    public void save(TaskInstance taskInstance) {
        taskInstanceRepository.save(taskInstance);
    }
}
