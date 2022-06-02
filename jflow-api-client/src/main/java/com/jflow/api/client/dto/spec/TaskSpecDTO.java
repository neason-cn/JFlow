package com.jflow.api.client.dto.spec;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskSpecDTO {
    private String taskName;
    private String taskType;
    private JSONObject onExecute;
    private JSONObject onSubmit;
    private JSONObject onQuery;
    private JSONObject onCancel;
}
