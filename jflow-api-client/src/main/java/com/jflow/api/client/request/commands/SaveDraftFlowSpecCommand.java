package com.jflow.api.client.request.commands;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.api.client.dto.spec.EdgeSpecDTO;
import com.jflow.api.client.dto.spec.NodeSpecDTO;
import lombok.Data;

import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class SaveDraftFlowSpecCommand {
    private String flowSpecCode;
    private String description;
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
    private String userId;
}
