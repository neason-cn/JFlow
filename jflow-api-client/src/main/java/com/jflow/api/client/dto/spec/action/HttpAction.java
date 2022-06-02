package com.jflow.api.client.dto.spec.action;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class HttpAction {
    private String url;
    private String method;
    private Map<String, List<String>> headers;
    private Map<String, String> params;
    private String body;
}
