package com.jflow.core.engine.flow.aggregate;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.common.exception.FlowException;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.enums.status.FlowSpecStatusEnum;
import com.jflow.core.engine.flow.spec.ActionSpec;
import com.jflow.core.engine.flow.spec.EdgeSpec;
import com.jflow.core.engine.flow.spec.NodeSpec;
import com.jflow.core.engine.graph.Graph;
import com.jflow.infra.spi.scheduler.Job;
import com.jflow.infra.spi.scheduler.SchedulerSpi;
import com.jflow.infra.spi.script.type.JsonScript;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Set;

import static com.jflow.common.error.Errors.ILLEGAL_FLOW_SPEC_STATUS_ERROR;

/**
 * The static spec of flow, the most important info is the nodes and edges.
 *
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@Data
public class FlowSpec implements Graph<NodeSpec, EdgeSpec>, FlowSpecAbility {

    /**
     * The unique id of this spec version.
     */
    private String flowSpecId;

    /**
     * The code of this spec version, the specs those have the same code are regarded as the same spec.
     */
    private String flowSpecCode;

    /**
     * The version of this spec version.
     */
    private int flowSpecVersion;

    /**
     * The description of this spec version.
     */
    private String description;

    /**
     * The status of this spec version.
     */
    private FlowSpecStatusEnum status;

    /**
     * Can have multi flow instance created by using this spec version or not.
     */
    private boolean enableMultiInstance;

    /**
     * The static context data which will be merged into the flow instance when create the instance immediately.
     */
    private JSONObject initContext;

    /**
     * The result of script will be regarded as the output of a flow instance.
     * The script will be executed when a flow instance went to the final status.
     */
    private JsonScript outputScript;

    /**
     * Scheduled to create a flow instance by using the spec version.
     */
    private boolean scheduled;

    /**
     * The cron expression.
     */
    private String cron;

    /**
     * The job id if enable scheduled.
     */
    private String cronJobId;

    /**
     * The time when create this spec version.
     */
    private Date createAt;

    /**
     * The time when release the spec version
     */
    private Date releaseAt;

    /**
     * The actions will be actioned when create a flow instance by using this spec version.
     */
    private Set<ActionSpec> onStart;

    /**
     * The actions will be actioned when a flow instance went to final status which created by using this spec version.
     */
    private Set<ActionSpec> onEnd;

    /**
     * All node spec.
     */
    private transient Set<NodeSpec> nodes;

    /**
     * All edge spec.
     */
    private transient Set<EdgeSpec> edges;

    @Override
    public void release(Context ctx) {
        if (this.status != FlowSpecStatusEnum.DRAFT) {
            throw new FlowException(ILLEGAL_FLOW_SPEC_STATUS_ERROR, this.status, this.getFlowSpecId());
        }
        this.status = FlowSpecStatusEnum.RELEASED;
        this.setReleaseAt(new Date());
        log.info("release spec id: {}", this.flowSpecId);
    }

    @Override
    public void archive(Context ctx) {
        if (this.status != FlowSpecStatusEnum.RELEASED) {
            throw new FlowException(ILLEGAL_FLOW_SPEC_STATUS_ERROR, this.status, this.getFlowSpecId());
        }
        this.status = FlowSpecStatusEnum.ARCHIVED;
        log.info("archive spec id: {}", this.flowSpecId);
        if (this.isScheduled()) {
            disableCron(ctx);
            log.info("auto disable schedule flow spec id: {} when archive", this.flowSpecId);
        }
    }

    @Override
    public void enableCron(Context ctx) {
        if (!this.scheduled) {
            this.scheduled = true;
            SchedulerSpi schedulerSpi = ctx.getRuntime().getSchedulerSpi();
            this.cronJobId = schedulerSpi.addJob(generateJob(ctx));
            log.info("enable schedule spec id: {}, job id: {}", this.flowSpecId, this.cronJobId);
            return;
        }
        log.info("the spec id: {} is scheduling", flowSpecId);
    }

    @Override
    public void disableCron(Context ctx) {
        if (this.scheduled) {
            this.scheduled = false;
            SchedulerSpi schedulerSpi = ctx.getRuntime().getSchedulerSpi();
            schedulerSpi.deleteJob(this.cronJobId);
            log.info("disable schedule spec id: {} and delete job id: {}", this.flowSpecId, this.cronJobId);
            return;
        }
        log.info("the spec id: {} is not scheduling yet", this.flowSpecId);
    }

    private Job generateJob(Context ctx) {
        Job job = new Job();
        job.setCron(this.cron);
        job.setWorker(() -> {
            ctx.getRuntime().getFlowInstanceService().start(this.getFlowSpecCode(), new JSONObject());
        });
        return job;
    }

}
