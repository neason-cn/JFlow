package com.jflow.core.domain.convertor;

import com.jflow.core.domain.dto.EdgeInstanceDTO;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.spec.EdgeSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class EdgeInstanceConvertor {

    public EdgeInstanceDTO convert(EdgeInstance instance) {
        if (null == instance) {
            return null;
        }
        EdgeInstanceDTO dto = new EdgeInstanceDTO();
        dto.setEdgeId(instance.getEdgeId());
        dto.setStatus(instance.getStatus());
        dto.setError(instance.getError());
        dto.setSourceNodeId(instance.getSourceNodeId());
        dto.setTargetNodeId(instance.getTargetNodeId());
        return dto;
    }

    public EdgeInstance convert(EdgeInstanceDTO dto, EdgeSpec spec) {
        if (null == dto) {
            return null;
        }
        EdgeInstance instance = new EdgeInstance(spec);
        instance.setStatus(dto.getStatus());
        instance.setError(dto.getError());
        instance.setSourceNodeId(dto.getSourceNodeId());
        instance.setTargetNodeId(dto.getTargetNodeId());
        return instance;
    }

}
