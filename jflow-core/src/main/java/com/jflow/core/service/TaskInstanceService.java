package com.jflow.core.service;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.flow.reference.instance.action.AbstractAction;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;

/**
 * @author neason
 * @since 0.0.1
 */
public interface TaskInstanceService {

    TaskInstance createAndSaveTask(TaskSpec spec, String flowInstanceId, String nodeId, JSONObject context);

    AbstractAction initAction(ActionSpec spec, JSONObject flowContext, JSONObject taskContext);

    void save(TaskInstance taskInstance);

}
