package com.jflow.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author neason
 * @since 0.0.1
 */
public class JsonUtil {

    public static JSONObject toJson(Object o) {
        if (null == o) {
            return null;
        }
        return JSONObject.parseObject(JSON.toJSONString(o));
    }

}
