package com.jflow.core.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.flow.factory.TaskInstanceFactory;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.reference.action.AbstractAction;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
import com.jflow.core.domain.flow.repository.TaskInstanceRepository;
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
        taskInstanceRepository.save(instance);
        return instance;
    }

    @Override
    public AbstractAction initAction(ActionSpec spec, JSONObject flowContext, JSONObject taskContext) {
        return null;
    }

    @Override
    public void save(TaskInstance taskInstance) {

    }
}
