package com.jflow.infra.spi.storage.entity;

import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskInstanceEntity {
    private String taskInstanceId;
    private String flowInstanceId;
    private String nodeId;
    private String status;
    private String type;
    private String taskContext;
    private String error;
    private String executeRequest;
    private String executeResponse;
    private String submitRequest;
    private String submitResponse;
    private String cancelRequest;
    private String cancelResponse;
}
