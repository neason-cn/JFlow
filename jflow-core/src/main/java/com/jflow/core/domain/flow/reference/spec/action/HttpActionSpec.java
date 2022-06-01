package com.jflow.core.domain.flow.reference.spec.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HttpActionSpec extends AbstractActionSpec {

    private static final long serialVersionUID = 2022001L;

    /**
     * Http url, eg: http:<a href="https://github.com/neason-cn/JFlow"></a>
     */
    private String url;

    /**
     * Http method, eg: GET, POST, DELETE.
     */
    private String method;

    /**
     * Http headers.
     */
    private Map<String, List<String>> headers;

    /**
     * Http params, eg: a=1&b=2&c=3
     */
    private Map<String, String> params;

    /**
     * Http body.
     */
    private String body;

}
