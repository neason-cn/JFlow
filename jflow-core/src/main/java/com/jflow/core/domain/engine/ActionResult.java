package com.jflow.core.domain.engine;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.enums.status.TaskInstanceStatusEnum;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class ActionResult {
    private TaskInstanceStatusEnum status;
    private String error;
    private JSONObject result;

    public static ActionResult error(String error) {
        ActionResult result = new ActionResult();
        result.setResult(new JSONObject());
        result.setStatus(TaskInstanceStatusEnum.FAILED);
        result.setError(error);
        return result;
    }
}
