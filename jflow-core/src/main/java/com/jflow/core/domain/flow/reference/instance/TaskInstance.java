package com.jflow.core.domain.flow.reference.instance;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.core.domain.engine.ActionResult;
import com.jflow.core.domain.engine.Callback;
import com.jflow.core.domain.engine.Context;
import com.jflow.core.domain.engine.activity.TaskActivity;
import com.jflow.core.domain.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.domain.enums.type.TaskTypeEnum;
import com.jflow.core.domain.flow.reference.instance.action.AbstractAction;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
import com.jflow.core.service.TaskInstanceService;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskInstance implements Type, TaskActivity {

    private String taskInstanceId;
    private String flowInstanceId;
    private String nodeId;
    private TaskSpec taskSpec;
    private TaskInstanceStatusEnum status;
    private String error;
    private JSONObject taskContext;

    @Override
    public String getType() {
        return taskSpec.getType();
    }

    @Override
    public void onFire(Context ctx) {
        if (taskSpec.getTaskType() == TaskTypeEnum.SYNC) {
            ActionResult actionResult = runAction(taskSpec.getOnExecute(), ctx);
            resolveResult(actionResult.getStatus(), actionResult.getError(), actionResult.getResult());
            return;
        }
        ActionResult actionResult = runAction(taskSpec.getOnSubmit(), ctx);
        resolveResult(actionResult.getStatus(), actionResult.getError(), actionResult.getResult());
    }

    private ActionResult runAction(ActionSpec spec, Context ctx) {
        TaskInstanceService taskInstanceService = ctx.getRuntime().getTaskInstanceService();
        JSONObject flowContext = ctx.getFlowInstance().getContext();
        AbstractAction action = taskInstanceService.initAction(spec, flowContext, taskContext);
        return action.onExecute(ctx);
    }

    private void resolveResult(TaskInstanceStatusEnum status, String error, JSONObject result) {
        this.status = status;
        this.error = error;
        mergeContext(result);
    }

    private void mergeContext(JSONObject addition) {
        this.taskContext.putAll(addition);
    }


    @Override
    public void onCancel(Context ctx) {
        runAction(taskSpec.getOnCancel(), ctx);
    }

    @Override
    public void onCallback(Context ctx, Callback callback) {
        resolveResult(callback.getStatus(), callback.getError(), callback.getResult());
    }
}
