package com.jflow.core.engine.flow.instance;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.common.log.LogContext;
import com.jflow.core.engine.activity.TaskActivity;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.ctx.Callback;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.ctx.ScriptContext;
import com.jflow.core.engine.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.engine.enums.type.TaskTypeEnum;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.action.ActionRecord;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.TaskSpec;
import com.jflow.core.engine.service.TaskInstanceService;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskInstance implements Type, TaskActivity {

    @JSONField(serialize = false)
    public final static String TASK_INSTANCE_ID = "instanceId";

    private String taskInstanceId;
    private String flowInstanceId;
    private String nodeId;
    private final TaskSpec taskSpec;
    private TaskInstanceStatusEnum status;
    private String error;
    private JSONObject taskContext;
    private Map<String, ActionRecord> records;

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
        LogContext.ti(taskInstanceId);
    }

    @Override
    public String getType() {
        return taskSpec.getType();
    }

    @Override
    public void onFire(Context ctx) {
        if (taskSpec.getTaskType() == TaskTypeEnum.SYNC) {
            ActionResponse onExecute = runAction(taskSpec.getOnExecute(), ctx, "onExecute");
            resolveResult(onExecute);
            ctx.getRuntime().getTaskInstanceService().save(this);
            return;
        }
        ActionResponse onSubmit = runAction(taskSpec.getOnSubmit(), ctx, "onSubmit");
        resolveResult(onSubmit);
        ctx.getRuntime().getTaskInstanceService().save(this);
    }

    private ActionResponse runAction(ActionSpec spec, Context ctx, String phase) {
        TaskInstanceService taskInstanceService = ctx.getRuntime().getTaskInstanceService();
        JSONObject flowContext = ctx.getFlowInstance().getContext();
        // init
        AbstractAction action = taskInstanceService.initAction(spec, new ScriptContext(flowContext, taskContext));
        // do
        ActionResponse actionResponse = action.onExecute(ctx);
        records.put(action.getActionInstanceId(),
                new ActionRecord(action.getActionSpec().getActionType(), action.toJson(), actionResponse, phase));
        return actionResponse;
    }

    private void resolveResult(ActionResponse response) {
        this.status = response.getStatus();
        this.error = response.getError();
        mergeContext(response.getResult());
    }

    private void mergeContext(JSONObject addition) {
        if (MapUtils.isEmpty(this.taskContext)) {
            JSONObject ctx = new JSONObject();
            ctx.put(TASK_INSTANCE_ID, this.taskInstanceId);
            this.taskContext = ctx;
        }
        if (MapUtils.isNotEmpty(addition)) {
            this.taskContext.putAll(addition);
        }
    }


    @Override
    public void onCancel(Context ctx) {
        runAction(taskSpec.getOnCancel(), ctx, "onCancel");
        ctx.getRuntime().getTaskInstanceService().save(this);
    }

    @Override
    public void onCallback(Context ctx, Callback callback) {
        ActionResponse callbackResponse = new ActionResponse();
        callbackResponse.setStatus(callback.getStatus());
        callbackResponse.setError(callback.getError());
        callbackResponse.setResult(callback.getResult());
        resolveResult(callbackResponse);
    }
}
