package com.jflow.core.domain.dto;

import com.jflow.core.engine.enums.status.EdgeInstanceStatusEnum;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeInstanceDTO {
    private String edgeId;
    private EdgeInstanceStatusEnum status;
    private String error;
    private String sourceNodeId;
    private String targetNodeId;
}
