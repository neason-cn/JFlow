package com.jflow.core.domain.engine;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.enums.status.TaskInstanceStatusEnum;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class ActionResponse {
    private TaskInstanceStatusEnum status;
    private String error;
    private JSONObject result;

    public static ActionResponse error(String error) {
        ActionResponse result = new ActionResponse();
        result.setResult(new JSONObject());
        result.setStatus(TaskInstanceStatusEnum.FAILED);
        result.setError(error);
        return result;
    }

    public static ActionResponse success(JSONObject data) {
        ActionResponse result = new ActionResponse();
        result.setResult(data);
        result.setStatus(TaskInstanceStatusEnum.SUCCESS);
        return result;
    }
}
