package com.jflow.core.engine.flow.instance.node;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.Callback;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.NodeInstanceStatusEnum;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.service.TaskInstanceService;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.ScriptSpi;
import com.jflow.infra.spi.script.type.BooleanScript;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import static com.jflow.core.engine.enums.status.TaskInstanceStatusEnum.FAILED;
import static com.jflow.core.engine.enums.status.TaskInstanceStatusEnum.SUCCESS;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskNode extends AbstractNodeInstance {

    @Override
    public void onSignal(Context ctx, EdgeInstance trigger) {
        if (null == this.getFirstSignalTime()) {
            this.setFirstSignalTime(new Date());
            initAllScript(ctx);
        }

        if (!finishWait()) {
            return;
        }

        if (this.isAutoSkip()) {
            this.onSkip(ctx, new JSONObject());
            return;
        }

        if (this.isAutoFire()) {
            this.onFire(ctx, new JSONObject());
            return;
        }

        this.setStatus(NodeInstanceStatusEnum.IDLE);
    }

    /**
     * Init all the result of script when first edge signal.
     */
    private void initAllScript(Context ctx) {
        this.setWaitAll(runScript(ctx, this.getSpec().getWaitAll()));
        this.setAutoFire(runScript(ctx, this.getSpec().getAutoFire()));
        this.setAutoSkip(runScript(ctx, this.getSpec().getAutoSkip()));
        this.setEnableSkip(runScript(ctx, this.getSpec().getEnableSkip()));
        this.setEnableRetry(runScript(ctx, this.getSpec().getEnableRetry()));
        this.setInterruptWhenSubmitFailed(runScript(ctx, this.getSpec().getInterruptWhenSubmitFailed()));
        this.setInterruptWhenExecuteFailed(runScript(ctx, this.getSpec().getInterruptWhenExecuteFailed()));
    }

    private boolean runScript(Context ctx, BooleanScript script) {
        ScriptSpi scriptSpi = ctx.getRuntime().getScriptSpi();
        ScriptResult<Boolean> result = scriptSpi.execute(script, ctx.getFlowInstance().getContext());
        if (result.hasError() || null == result.getResult()) {
            return false;
        }
        return result.getResult();
    }

    private boolean finishWait() {
        if (this.isWaitAll()) {
            return this.getIncoming().stream().allMatch(edge -> edge.getStatus().isAccess());
        }
        return this.getIncoming().stream().anyMatch(edge -> edge.getStatus().isAccess());
    }

    @Override
    public void onFire(Context ctx, JSONObject args) {
        // before run task and ignore the result.
        runAction(ctx, this.getSpec().getBefore());

        // run task
        TaskInstance taskInstance = ctx.getRuntime().getTaskInstanceService().createAndSaveTask(this.getSpec().getTaskSpec(),
                ctx.getFlowInstance().getFlowInstanceId(), this.getNodeId(), ctx.getFlowInstance().getContext());
        this.setLatestTask(taskInstance);
        taskInstance.onFire(ctx);

        // after run task
        afterTaskRun(ctx, taskInstance);
    }

    private void afterTaskRun(Context ctx, TaskInstance taskInstance) {
        if (SUCCESS == taskInstance.getStatus()) {
            this.setStatus(NodeInstanceStatusEnum.SUCCESS);
            // run after action and ignore the result.
            runAction(ctx, this.getSpec().getAfter());
            fireOutgoingEdges(ctx);
            return;
        }

        if (FAILED == taskInstance.getStatus()) {
            this.setStatus(NodeInstanceStatusEnum.FAILED);
            return;
        }

        this.setStatus(NodeInstanceStatusEnum.RUNNING);
    }


    @Override
    public void onSkip(Context ctx, JSONObject args) {
        this.setStatus(NodeInstanceStatusEnum.SKIPPED);
        fireOutgoingEdges(ctx);
    }

    @Override
    public void onRetry(Context ctx, JSONObject args) {
        TaskInstanceService instanceService = ctx.getRuntime().getTaskInstanceService();
        TaskInstance taskInstance = instanceService.createAndSaveTask(this.getSpec().getTaskSpec(),
                ctx.getFlowInstance().getFlowInstanceId(), this.getNodeId(), ctx.getFlowInstance().getContext());
        this.setLatestTask(taskInstance);
        taskInstance.onFire(ctx);
        afterTaskRun(ctx, taskInstance);
    }

    @Override
    public void onCancel(Context ctx, JSONObject args) {
        this.setStatus(NodeInstanceStatusEnum.CANCELED);
        this.getLatestTask().onCancel(ctx);
    }

    @Override
    public void onCallback(Context ctx, Callback callback) {
        getLatestTask().onCallback(ctx, callback);
        afterTaskRun(ctx, getLatestTask());
    }

}
