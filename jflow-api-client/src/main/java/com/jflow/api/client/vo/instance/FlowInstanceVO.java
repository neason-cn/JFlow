package com.jflow.api.client.vo.instance;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowInstanceVO {
    private String flowInstanceId;
    private String parentTaskInstanceId;
    private String status;
    private JSONObject input;
    private JSONObject output;
    private JSONObject context;
    private Set<NodeInstanceVO> nodes;
    private Set<EdgeInstanceVO> edges;
    private Date createAt;
    private Date cancelAt;
}
