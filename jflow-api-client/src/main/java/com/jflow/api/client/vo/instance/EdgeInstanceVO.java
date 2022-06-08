package com.jflow.api.client.vo.instance;

import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class EdgeInstanceVO {
    private String edgeId;
    private String status;
    private String error;
    private String sourceNodeId;
    private String targetNodeId;
}
