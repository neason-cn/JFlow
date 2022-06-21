package com.jflow.core.engine.flow.instance.node;

import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.ActionSpec;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
public class EndNode extends AbstractNodeInstance {

    @Override
    public void onSignal(Context ctx, EdgeInstance trigger) {
        FlowInstance flowInstance = ctx.getFlowInstance();
        Set<ActionSpec> endActions = flowInstance.getSpec().getOnEnd();
        endActions.forEach(action -> {
            runAndIgnoreResult(ctx, action);
            log.info("execute the endAction: {} of flow", action.getName());
        });
        flowInstance.setStatus(FlowInstanceStatusEnum.SUCCESS);
        flowInstance.onFinish(ctx);
        log.info("finish the flow instance");
    }

}
