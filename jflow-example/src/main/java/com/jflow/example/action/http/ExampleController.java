package com.jflow.example.action.http;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/example")
public class ExampleController {

    @PostMapping("/sync.json")
    public JSONObject sync(@RequestBody JSONObject request) {
        log.info(request.toJSONString());
        return request;
    }

    @PostMapping("/async.json")
    public JSONObject async(@RequestBody JSONObject request, @RequestHeader("x-jflow-task-id") String taskInstanceId) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                TaskController.completeTask(taskInstanceId, "SUCCESS", request);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return request;
    }

}
