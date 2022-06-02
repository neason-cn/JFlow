package com.jflow.core.domain.flow.factory;

import cn.hutool.core.util.IdUtil;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.enums.status.FlowSpecStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.convertor.ActionSpecConvertor;
import com.jflow.core.domain.flow.convertor.EdgeSpecConvertor;
import com.jflow.core.domain.flow.convertor.NodeSpecConvertor;
import com.jflow.core.domain.flow.reference.spec.EdgeSpec;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import com.jflow.core.domain.graph.Graph;
import com.jflow.infra.spi.script.type.JsonScript;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowSpecFactory {

    private final NodeSpecConvertor nodeSpecConvertor;
    private final EdgeSpecConvertor edgeSpecConvertor;
    private final ActionSpecConvertor actionSpecConvertor;

    public FlowSpec create(FlowSpecVO vo, int nextVersion, String userId) {
        FlowSpec flowSpec = new FlowSpec();

        // base info
        flowSpec.setFlowSpecId(IdUtil.fastSimpleUUID());
        flowSpec.setFlowSpecCode(vo.getFlowSpecCode());
        flowSpec.setFlowSpecVersion(nextVersion);
        flowSpec.setDescription(vo.getDescription());
        flowSpec.setStatus(FlowSpecStatusEnum.DRAFT);
        flowSpec.setInitContext(vo.getInitContext());
        flowSpec.setOutputScript(new JsonScript(vo.getOutputScript()));
        flowSpec.setScheduled(vo.isScheduled());
        flowSpec.setCron(vo.getCron());
        flowSpec.setCreateBy(new FlowUser(userId));
        flowSpec.setCreateAt(new Date());
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

        return flowSpec;
    }

}
