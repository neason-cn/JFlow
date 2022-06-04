package com.jflow.api.client.vo.spec;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class ActionSpecVO {
    private String actionType;
    private JSONObject params;
}
