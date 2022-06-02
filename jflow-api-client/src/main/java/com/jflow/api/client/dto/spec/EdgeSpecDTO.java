package com.jflow.api.client.dto.spec;

import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeSpecDTO {
    private int priority;
    private String script;
    private String edgeId;
    private String edgeName;
    private String sourceNodeId;
    private String targetNodeId;
}
