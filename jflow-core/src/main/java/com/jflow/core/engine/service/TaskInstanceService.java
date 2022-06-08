package com.jflow.core.engine.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.RuntimeContext;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.TaskSpec;

/**
 * @author neason
 * @since 0.0.1
 */
public interface TaskInstanceService {

    TaskInstance createAndSaveTask(TaskSpec spec, String flowInstanceId, String nodeId, JSONObject context);

    AbstractAction initAction(ActionSpec spec, RuntimeContext context);

    void save(TaskInstance taskInstance);

}
