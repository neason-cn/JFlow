package com.jflow.app.endpoint.http;

import com.jflow.api.client.request.commands.UpdateTaskCommand;
import com.jflow.api.client.response.Json;
import com.jflow.core.service.FlowInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neason
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskInstanceController {

    private final FlowInstanceService flowInstanceService;

    @PostMapping("/complete.json")
    public Json<Boolean> complete(@RequestBody UpdateTaskCommand command) {
        flowInstanceService.completeTask(command.getTaskInstanceId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

}
