package com.jflow.example.action.http;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neason
 * @since 0.0.1
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

    @PostMapping("/sync.json")
    public JSONObject sync(@RequestBody JSONObject request) {
        return request;
    }

    @PostMapping("/async.json")
    public JSONObject async(@RequestBody JSONObject request) {
        return request;
    }

}
