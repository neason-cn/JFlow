package com.jflow.infra.spi.storage.entity;

import com.jflow.common.annotation.Column;
import com.jflow.common.annotation.Id;
import com.jflow.common.annotation.Table;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@Table("task_ins")
public class TaskInstanceEntity {
    @Id("task_instance_id")
    private String taskInstanceId;
    @Column("flow_instance_id")
    private String flowInstanceId;
    @Column("node_id")
    private String nodeId;
    @Column("status")
    private String status;
    @Column("type")
    private String type;
    @Column("task_context")
    private String taskContext;
    @Column("error")
    private String error;
    @Column("records")
    private String records;
}
