package com.jflow.app.endpoint.http;

import com.jflow.api.client.request.commands.ReleaseFlowSpecCommand;
import com.jflow.api.client.request.commands.SaveDraftFlowSpecCommand;
import com.jflow.api.client.request.queries.QueryFlowSpecById;
import com.jflow.api.client.request.queries.QueryLatestFlowSpecByCode;
import com.jflow.api.client.response.Json;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.convertor.FlowSpecConvertor;
import com.jflow.core.domain.flow.repository.FlowSpecRepository;
import com.jflow.core.service.FlowSpecService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.jflow.common.error.Errors.NO_RELEASED_FLOW_SPEC_VERSION_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/flow/spec")
@RequiredArgsConstructor
public class FlowSpecController {

    private final FlowSpecService flowSpecService;
    private final FlowSpecConvertor flowSpecConvertor;
    private final FlowSpecRepository flowSpecRepository;

    //------------------------ COMMAND ------------------------

    @PostMapping("/save.json")
    public Json<String> save(@RequestBody SaveDraftFlowSpecCommand command) {
        if (null == command) {
            return Json.error("the save command can not be null.");
        }
        String flowSpecId = flowSpecService.saveDraft(command.getFlowSpec(), new FlowUser(command.getUserId()));
        return Json.success(flowSpecId);
    }

    @PostMapping("release.json")
    public Json<Boolean> release(@RequestBody ReleaseFlowSpecCommand command) {
        if (null == command || StringUtils.isEmpty(command.getFlowSpecId())) {
            return Json.error("the flowSpecId can not be empty.");
        }
        flowSpecService.release(command.getFlowSpecId(), new FlowUser(command.getUserId()));
        return Json.success(true);
    }

    //------------------------  QUERY  ------------------------

    @PostMapping("/getById.json")
    public Json<FlowSpecVO> getById(@RequestBody QueryFlowSpecById query) {
        if (null == query || StringUtils.isEmpty(query.getFlowSpecId())) {
            return Json.error("the flowSpecId can not be empty.");
        }
        FlowSpec flowSpec = flowSpecRepository.getById(query.getFlowSpecId());
        return Json.success(flowSpecConvertor.convert(flowSpec));
    }

    @PostMapping("/getLatestFlowSpecByCode.json")
    public Json<FlowSpecVO> getLatestFlowSpecByCode(@RequestBody QueryLatestFlowSpecByCode query) {
        if (null == query || StringUtils.isEmpty(query.getFlowSpecCode())) {
            return Json.error("the flowSpecCode can not be empty.");
        }
        Optional<FlowSpec> flowSpec = flowSpecRepository.getReleaseByCode(query.getFlowSpecCode());
        return flowSpec
                .map(spec -> Json.success(flowSpecConvertor.convert(spec)))
                .orElseGet(() -> Json.error(String.format(NO_RELEASED_FLOW_SPEC_VERSION_ERROR.errorMessage(), query.getFlowSpecCode())));
    }

}
