package com.jflow.app.endpoint.http;

import com.jflow.api.client.request.commands.ReleaseFlowSpecCommand;
import com.jflow.api.client.request.commands.SaveDraftFlowSpecCommand;
import com.jflow.api.client.request.queries.QueryFlowSpecByCodeAndVersion;
import com.jflow.api.client.request.queries.QueryFlowSpecById;
import com.jflow.api.client.response.Json;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.service.FlowSpecService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neason
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/flow/spec")
@RequiredArgsConstructor
public class FlowSpecController {

    private final FlowSpecService flowSpecService;

    //------------------------ COMMAND ------------------------

    @PostMapping("/save.json")
    public Json<String> save(@RequestBody SaveDraftFlowSpecCommand command) {
        if (null == command) {
            return Json.error("the save command can not be null.");
        }
        String flowSpecId = flowSpecService.saveDraft(command);
        return Json.success(flowSpecId);
    }

    @PostMapping("release.json")
    public Json<Boolean> release(@RequestBody ReleaseFlowSpecCommand command) {
        if (null == command || StringUtils.isEmpty(command.getFlowSpecId())) {
            return Json.error("the flowSpecId can not be empty.");
        }
        flowSpecService.release(command);
        return Json.success(true);
    }

    //------------------------  QUERY  ------------------------

    @PostMapping("/getById.json")
    public Json<FlowSpecVO> getById(@RequestBody QueryFlowSpecById query) {
        return Json.success(null);
    }

    @PostMapping("/getByCodeAndVersion.json")
    public Json<FlowSpecVO> getByCodeAndVersion(@RequestBody QueryFlowSpecByCodeAndVersion query) {
        return Json.success(null);
    }

}
