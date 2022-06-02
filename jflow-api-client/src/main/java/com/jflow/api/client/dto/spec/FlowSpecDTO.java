package com.jflow.api.client.dto.spec;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowSpecDTO {
    private String flowSpecId;
    private String flowSpecCode;
    private String description;
    private int flowSpecVersion;
    private String status;
    private boolean enableMultiInstance;
    private JSONObject initContext;
    private String outputScript;
    private boolean scheduled;
    private String cron;
    private String tenant;
    private JSONObject onStartAction;
    private JSONObject onEndAction;
    private Set<NodeSpecDTO> nodes;
    private Set<EdgeSpecDTO> edges;
}
