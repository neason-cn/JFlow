package com.jflow.infra.spi.storage.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowInstanceEntity {
    private String flowInstanceId;
    private String flowSpecId;
    private String taskInstanceId;
    private String instanceStatus;
    private String context;
    private String input;
    private String output;
    private String nodes;
    private String edges;
    private Date createAt;
    private Date cancelAt;
}
