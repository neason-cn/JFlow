package com.jflow.app.endpoint.http;

import com.jflow.api.client.request.commands.UpdateTaskCommand;
import com.jflow.api.client.request.queries.QueryAllTasksOfFlowInstance;
import com.jflow.api.client.response.Json;
import com.jflow.api.client.vo.instance.TaskInstanceVO;
import com.jflow.app.endpoint.http.convertor.TaskVOConvertor;
import com.jflow.core.engine.flow.instance.TaskInstance;
import com.jflow.core.engine.service.FlowInstanceService;
import com.jflow.core.engine.service.TaskInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskInstanceController {

    private final FlowInstanceService flowInstanceService;
    private final TaskInstanceService taskInstanceService;
    private final TaskVOConvertor taskVOConvertor;

    @PostMapping("/complete.json")
    public Json<Boolean> complete(@RequestBody UpdateTaskCommand command) {
        flowInstanceService.completeTask(command.getTaskInstanceId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

    @PostMapping("/queryAll.json")
    public Json<Map<String, List<TaskInstanceVO>>> getAllTask(@RequestBody QueryAllTasksOfFlowInstance query) {
        Optional<List<TaskInstance>> taskInstances = taskInstanceService.queryAll(query.getFlowInstanceId());
        if (taskInstances.isPresent()) {
            List<TaskInstanceVO> tasks = taskInstances.get().stream()
                    .map(taskVOConvertor::convert)
                    .collect(Collectors.toList());
            return Json.success(taskVOConvertor.groupByNodeId(tasks));
        }
        return Json.success(new HashMap<>());
    }

}
