package com.jflow.core.engine.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.ScriptContext;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.TaskSpec;

import java.util.List;
import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
public interface TaskInstanceService {

    TaskInstance createAndSaveTask(TaskSpec spec, String flowInstanceId, String nodeId, JSONObject context);

    AbstractAction initAction(ActionSpec spec, ScriptContext context);

    void save(TaskInstance taskInstance);

    Optional<List<TaskInstance>> queryAll(String flowInstanceId);

}
