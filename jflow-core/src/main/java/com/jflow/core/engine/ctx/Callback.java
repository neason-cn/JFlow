package com.jflow.core.engine.ctx;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.enums.status.TaskInstanceStatusEnum;
import lombok.Data;

/**
 * The callback of a task instance.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class Callback {
    private String taskInstanceId;
    private TaskInstanceStatusEnum status;
    private String error;
    private JSONObject result;
}
