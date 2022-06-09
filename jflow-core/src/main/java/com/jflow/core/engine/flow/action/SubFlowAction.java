package com.jflow.core.engine.flow.action;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.service.FlowInstanceService;

/**
 * @author neason
 * @since 0.0.1
 */
public class SubFlowAction extends AbstractAction {

    private String flowSpecCode;

    private JSONObject args;

    @Override
    public ActionResponse onExecute(Context ctx) {
        FlowInstanceService service = ctx.getRuntime().getFlowInstanceService();
        FlowInstance start = service.start(flowSpecCode, args);
        JSONObject data = new JSONObject();
        data.put("flowInstanceId", start.getFlowInstanceId());
        return ActionResponse.success(data);
    }

}
