package com.jflow.api.client.vo.spec;

import lombok.Data;

import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class NodeSpecVO {
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
    private ActionSpecVO beforeTaskAction;
    private TaskSpecVO taskSpec;
    private ActionSpecVO afterTaskAction;
}
