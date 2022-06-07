package com.jflow.core.domain.dto;

import com.jflow.core.engine.enums.status.NodeInstanceStatusEnum;
import com.jflow.core.engine.enums.type.NodeTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class NodeInstanceDTO {
    private String nodeId;
    private NodeTypeEnum nodeType;
    private NodeInstanceStatusEnum status;
    private TaskInstanceDTO latestTask;
    private Boolean waitAll;
    private Boolean autoFire;
    private Boolean autoSkip;
    private Boolean enableSkip;
    private Boolean enableRetry;
    private Boolean interruptWhenSubmitFailed;
    private Boolean interruptWhenExecuteFailed;
    private Date firstSignalTime;
    private Date fireTime;
    private Date finishTime;
}
