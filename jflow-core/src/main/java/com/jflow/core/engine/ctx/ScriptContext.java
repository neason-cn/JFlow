package com.jflow.core.engine.ctx;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
public class ScriptContext {
    private JSONObject flowContext;
    private JSONObject taskContext;
}
