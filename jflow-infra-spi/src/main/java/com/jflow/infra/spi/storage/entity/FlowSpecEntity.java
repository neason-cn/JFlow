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
@Table("flow_spec")
public class FlowSpecEntity {
    @Id("spec_id")
    private String specId;
    @Column("spec_code")
    private String specCode;
    @Column("spec_version")
    private int specVersion;
    @Column("spec_desc")
    private String specDesc;
    @Column("spec_status")
    private String specStatus;
    @Column("enable_multi_instance")
    private boolean enableMultiInstance;
    @Column("init_context")
    private String initContext;
    @Column("output_script")
    private String outputScript;
    @Column("scheduled")
    private boolean scheduled;
    @Column("cron")
    private String cron;
    @Column("start_action")
    private String startAction;
    @Column("end_action")
    private String endAction;
    @Column("nodes")
    private String nodes;
    @Column("edges")
    private String edges;
    @Column("create_at")
    private Date createAt;
    @Column("release_at")
    private Date releaseAt;
}
