package com.jflow.app.endpoint.http;

import com.jflow.api.client.request.commands.FlowInstanceCommand;
import com.jflow.api.client.request.commands.FlowInstanceNodeCommand;
import com.jflow.api.client.request.commands.StartFlowInstanceCommand;
import com.jflow.api.client.request.queries.QueryFlowInstanceById;
import com.jflow.api.client.response.Json;
import com.jflow.api.client.vo.instance.FlowInstanceVO;
import com.jflow.core.domain.convertor.FlowInstanceConvertor;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.service.FlowInstanceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neason
 * @since 0.0.1
 */
@Api(tags = "FlowInstance")
@RestController
@RequestMapping("/api/flow/instance")
@RequiredArgsConstructor
public class FlowInstanceController extends AbstractController{

    private final FlowInstanceService flowInstanceService;
    private final FlowInstanceConvertor flowInstanceConvertor;

    //------------------------ COMMAND ------------------------

    @PostMapping("/start.json")
    public Json<String> start(@RequestBody StartFlowInstanceCommand command) {
        FlowInstance flowInstance = flowInstanceService.start(command.getFlowSpecCode(), command.getContext());
        return Json.success(flowInstance.getFlowInstanceId());
    }

    @PostMapping("/fire.json")
    public Json<Boolean> fire(@RequestBody FlowInstanceNodeCommand command) {
        flowInstanceService.fireNode(command.getFlowInstanceId(), command.getNodeId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

    @PostMapping("/retry.json")
    public Json<Boolean> retry(@RequestBody FlowInstanceNodeCommand command) {
        flowInstanceService.retryNode(command.getFlowInstanceId(), command.getNodeId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

    @PostMapping("/skip.json")
    public Json<Boolean> skip(@RequestBody FlowInstanceNodeCommand command) {
        flowInstanceService.skipNode(command.getFlowInstanceId(), command.getNodeId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

    @PostMapping("/cancel.json")
    public Json<Boolean> cancel(@RequestBody FlowInstanceNodeCommand command) {
        flowInstanceService.cancelNode(command.getFlowInstanceId(), command.getNodeId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

    @PostMapping("/terminate.json")
    public Json<Boolean> terminate(@RequestBody FlowInstanceCommand command) {
        flowInstanceService.terminate(command.getFlowInstanceId(), command.getContext());
        return Json.success(Boolean.TRUE);
    }

    //------------------------  QUERY  ------------------------

    @PostMapping("/getById.json")
    public Json<FlowInstanceVO> getById(@RequestBody QueryFlowInstanceById query) {
        FlowInstance flowInstance = flowInstanceService.getById(query.getFlowInstanceId());
        return Json.success(flowInstanceConvertor.convert(flowInstance));
    }

}
