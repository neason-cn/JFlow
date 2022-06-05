package com.jflow.core.domain.flow.reference.instance;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.enums.Type;
import com.jflow.core.domain.engine.Callback;
import com.jflow.core.domain.engine.Context;
import com.jflow.core.domain.engine.activity.TaskActivity;
import com.jflow.core.domain.enums.status.TaskInstanceStatusEnum;
import com.jflow.core.domain.flow.reference.spec.TaskSpec;
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

    }

    @Override
    public void onCancel(Context ctx) {

    }

    @Override
    public void onCallback(Context ctx, Callback callback) {

    }
}
