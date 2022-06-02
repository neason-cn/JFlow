package com.jflow.api.client.request.queries;

import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class QueryFlowSpecByCodeAndVersion {
    private String flowSpecCode;
    private String flowSpecVersion;
}
