package com.jflow.api.client.vo.instance;

import lombok.Data;

import java.util.Date;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class NodeInstanceVO {
    private String nodeId;
    private String nodeType;
    private String status;
    private TaskInstanceVO latestTask;
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
