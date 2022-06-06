package com.jflow.infra.spi.storage.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowSpecEntity {
    private String specId;
    private String specCode;
    private int specVersion;
    private String specDesc;
    private String specStatus;
    private boolean enableMultiInstance;
    private String initContext;
    private String outputScript;
    private boolean scheduled;
    private String cron;
    private String startAction;
    private String endAction;
    private String nodes;
    private String edges;
    private Date createAt;
    private Date releaseAt;
}
