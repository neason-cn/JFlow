package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.EdgeSpecVO;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.api.client.vo.spec.NodeSpecVO;
import com.jflow.core.domain.enums.status.FlowSpecStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.reference.spec.EdgeSpec;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import com.jflow.core.domain.graph.Graph;
import com.jflow.infra.spi.script.type.JsonScript;
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

    public FlowSpec merge(FlowSpecVO newInfo, FlowSpec oldSpec) {
        FlowSpec newSpec = new FlowSpec();

        // copy first
        copy(newSpec, newInfo);

        // then set
        newSpec.setFlowSpecId(oldSpec.getFlowSpecId());
        newSpec.setCreateBy(oldSpec.getCreateBy());
        newSpec.setCreateAt(oldSpec.getCreateAt());
        newSpec.setStatus(oldSpec.getStatus());
        newSpec.setFlowSpecVersion(oldSpec.getFlowSpecVersion());
        newSpec.setReleaseAt(oldSpec.getReleaseAt());
        newSpec.setReleaseBy(oldSpec.getReleaseBy());

        return newSpec;
    }

    public FlowSpecVO convert(FlowSpec spec) {
        if (null == spec) {
            return null;
        }
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

    public void copy(FlowSpec flowSpec, FlowSpecVO vo) {
        flowSpec.setFlowSpecId(vo.getFlowSpecId());
        flowSpec.setStatus(FlowSpecStatusEnum.of(vo.getStatus()));
        flowSpec.setFlowSpecVersion(vo.getFlowSpecVersion());
        flowSpec.setFlowSpecCode(vo.getFlowSpecCode());
        flowSpec.setDescription(vo.getDescription());
        flowSpec.setInitContext(vo.getInitContext());
        flowSpec.setOutputScript(new JsonScript(vo.getOutputScript()));
        flowSpec.setScheduled(vo.isScheduled());
        flowSpec.setCron(vo.getCron());
        flowSpec.setTenant(vo.getTenant());

        // action
        flowSpec.setOnStart(actionSpecConvertor.convert(vo.getOnStartAction()));
        flowSpec.setOnEnd(actionSpecConvertor.convert(vo.getOnEndAction()));

        // nodes and edges
        Set<NodeSpec> nodes = vo.getNodes().stream()
                .map(nodeSpecConvertor::convert)
                .collect(Collectors.toSet());
        Set<EdgeSpec> edges = vo.getEdges().stream()
                .map(edgeSpecConvertor::convert)
                .collect(Collectors.toSet());

        // !!! connect nodes and edges
        Graph.connect(nodes, edges);
        flowSpec.setNodes(nodes);
        flowSpec.setEdges(edges);
    }

}