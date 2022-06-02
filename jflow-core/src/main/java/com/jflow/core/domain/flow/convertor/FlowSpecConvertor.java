package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.EdgeSpecVO;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.api.client.vo.spec.NodeSpecVO;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowSpecConvertor {

    private final ActionSpecConvertor actionSpecConvertor;
    private final NodeSpecConvertor nodeSpecConvertor;
    private final EdgeSpecConvertor edgeSpecConvertor;

    public FlowSpec fetch(FlowSpecVO newInfo, FlowSpec oldSpec) {
        return new FlowSpec();
    }

    public FlowSpecVO convert(FlowSpec spec) {
        FlowSpecVO vo = new FlowSpecVO();
        vo.setFlowSpecId(spec.getFlowSpecId());
        vo.setFlowSpecCode(spec.getFlowSpecCode());
        vo.setDescription(spec.getDescription());
        vo.setFlowSpecVersion(spec.getFlowSpecVersion());
        vo.setStatus(spec.getStatus().getStatus());
        vo.setEnableMultiInstance(spec.isEnableMultiInstance());
        vo.setInitContext(spec.getInitContext());
        vo.setOutputScript(spec.getOutputScript() != null ? spec.getOutputScript().getContent() : null);
        vo.setScheduled(spec.isScheduled());
        vo.setOnStartAction(actionSpecConvertor.convert(spec.getOnStart()));
        vo.setOnEndAction(actionSpecConvertor.convert(spec.getOnEnd()));

        Set<NodeSpecVO> nodes = spec.getNodes().stream()
                .map(nodeSpecConvertor::convert)
                .collect(Collectors.toSet());
        vo.setNodes(nodes);

        Set<EdgeSpecVO> edges = spec.getEdges().stream()
                .map(edgeSpecConvertor::convert)
                .collect(Collectors.toSet());
        vo.setEdges(edges);

        return vo;
    }

}
