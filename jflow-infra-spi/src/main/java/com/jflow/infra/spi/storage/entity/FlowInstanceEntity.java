package com.jflow.infra.spi.storage.entity;

import com.jflow.common.annotation.Column;
import com.jflow.common.annotation.Id;
import com.jflow.common.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@Table("flow_ins")
public class FlowInstanceEntity {
    @Id("flow_instance_id")
    private String flowInstanceId;
    @Column("flow_spec_id")
    private String flowSpecId;
    @Column("task_instance_id")
    private String taskInstanceId;
    @Column("status")
    private String status;
    @Column("context")
    private String context;
    @Column("input")
    private String input;
    @Column("output")
    private String output;
    @Column("nodes")
    private String nodes;
    @Column("edges")
    private String edges;
    @Column("create_at")
    private Date createAt;
    @Column("cancel_at")
    private Date cancelAt;
}
