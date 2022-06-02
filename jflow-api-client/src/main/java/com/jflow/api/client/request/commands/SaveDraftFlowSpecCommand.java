package com.jflow.api.client.request.commands;

import com.jflow.api.client.vo.spec.FlowSpecVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SaveDraftFlowSpecCommand extends AuthCommand {
    private FlowSpecVO flowSpec;
}
