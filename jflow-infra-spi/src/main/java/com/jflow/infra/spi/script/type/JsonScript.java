package com.jflow.infra.spi.script.type;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;

/**
 * @author neason
 * @since 0.0.1
 */
public class JsonScript extends Script<JSONObject> {

    public JsonScript(String content) {
        super(content);
    }

    @Override
    public TypeReference<JSONObject> getResultType() {
        return new TypeReference<JSONObject>() {
        };
    }

}
