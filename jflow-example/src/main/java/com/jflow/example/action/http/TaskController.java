package com.jflow.example.action.http;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author neason
 * @since 0.0.1
 */
public class TaskController {

    public static void completeTask(String taskInstanceId, String taskStatus, JSONObject context) {
        JSONObject request = new JSONObject();
        request.put("taskInstanceId", taskInstanceId);
        request.put("context", context);
        request.put("taskStatus", taskStatus);
        HttpUtil.createPost("localhost:8080/api/task/complete.json").body(request.toJSONString()).execute();
    }

}
