package com.jflow.api.client.vo.spec;

import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class TaskSpecVO {
    private String taskName;
    private String taskType;
    private ActionSpecVO onExecute;
    private ActionSpecVO onSubmit;
    private ActionSpecVO onQuery;
    private ActionSpecVO onCancel;
}
