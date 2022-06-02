package com.jflow.api.client.dto.spec;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class NodeSpecDTO {
    private String nodeId;
    private String nodeName;
    private String waitAll;
    private String autoFireScript;
    private String autoSkipScript;
    private String enableSkipScript;
    private String enableRetryScript;
    private String interruptWhenSubmitFailedScript;
    private String interruptWhenExecuteFailedScript;
    private Map<String, String> labels;
    private JSONObject beforeTaskAction;
    private TaskSpecDTO taskSpec;
    private JSONObject afterTaskAction;
}
