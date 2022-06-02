package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.EdgeSpecVO;
import com.jflow.core.domain.flow.reference.spec.EdgeSpec;
import com.jflow.infra.spi.script.type.BooleanScript;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class EdgeSpecConvertor {

    public EdgeSpec convert(EdgeSpecVO vo) {
        EdgeSpec edgeSpec = new EdgeSpec();
        edgeSpec.setEdgeId(vo.getEdgeId());
        edgeSpec.setEdgeName(vo.getEdgeName());
        edgeSpec.setPriority(vo.getPriority());
        edgeSpec.setScript(new BooleanScript(vo.getScript()));
        edgeSpec.setSourceNodeId(vo.getSourceNodeId());
        edgeSpec.setTargetNodeId(vo.getTargetNodeId());
        return edgeSpec;
    }

    public EdgeSpecVO convert(EdgeSpec spec) {
        EdgeSpecVO vo = new EdgeSpecVO();
        vo.setEdgeId(spec.getEdgeId());
        vo.setEdgeName(spec.getEdgeName());
        vo.setPriority(spec.getPriority());
        vo.setScript(spec.getScript() != null ? spec.getScript().getContent() : null);
        vo.setSourceNodeId(spec.getSourceNodeId());
        vo.setTargetNodeId(spec.getTargetNodeId());
        return vo;
    }

}
