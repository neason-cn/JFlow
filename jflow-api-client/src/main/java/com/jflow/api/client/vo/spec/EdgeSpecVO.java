package com.jflow.api.client.vo.spec;

import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeSpecVO {
    private String edgeId;
    private String edgeName;
    private String script;
    private String sourceNodeId;
    private String targetNodeId;
}
