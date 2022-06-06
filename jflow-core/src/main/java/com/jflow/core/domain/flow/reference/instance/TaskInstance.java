package com.jflow.core.domain.flow.reference.instance;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.core.domain.engine.ActionResponse;
import com.jflow.core.domain.engine.Callback;
import com.jflow.core.domain.engine.Context;
import com.jflow.core.domain.engine.activity.TaskActivity;
import com.jflow.core.domain.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.domain.enums.type.TaskTypeEnum;
import com.jflow.core.domain.flow.reference.action.AbstractAction;
import com.jflow.core.domain.flow.reference.action.ActionRecord;
import com.jflow.core.domain.flow.reference.spec.ActionSpec;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
import com.jflow.core.service.TaskInstanceService;
import lombok.Data;

import java.util.Map;

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
    private Map<String, ActionRecord> records;


    @Override
    public String getType() {
        return taskSpec.getType();
    }

    @Override
    public void onFire(Context ctx) {
        if (taskSpec.getTaskType() == TaskTypeEnum.SYNC) {
            AbstractAction action = createAction(taskSpec.getOnExecute(), ctx);
            ActionResponse actionResponse = action.onExecute(ctx);
            resolveResult(actionResponse.getStatus(), actionResponse.getError(), actionResponse.getResult());
            records.put("onFire",new ActionRecord(action.getActionSpec().getActionType(), action.toJson(), actionResponse));
            return;
        }
        AbstractAction action = createAction(taskSpec.getOnSubmit(), ctx);
        ActionResponse actionResponse = action.onExecute(ctx);
        resolveResult(actionResponse.getStatus(), actionResponse.getError(), actionResponse.getResult());
        records.put("onFire",new ActionRecord(action.getActionSpec().getActionType(), action.toJson(), actionResponse));
    }

    private AbstractAction createAction(ActionSpec spec, Context ctx) {
        TaskInstanceService taskInstanceService = ctx.getRuntime().getTaskInstanceService();
        JSONObject flowContext = ctx.getFlowInstance().getContext();
        return taskInstanceService.initAction(spec, flowContext, taskContext);
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
        AbstractAction action = createAction(taskSpec.getOnCancel(), ctx);
        ActionResponse actionResponse = action.onExecute(ctx);
        records.put("onCancel",new ActionRecord(action.getActionSpec().getActionType(), action.toJson(), actionResponse));
    }

    @Override
    public void onCallback(Context ctx, Callback callback) {
        resolveResult(callback.getStatus(), callback.getError(), callback.getResult());
    }
}
