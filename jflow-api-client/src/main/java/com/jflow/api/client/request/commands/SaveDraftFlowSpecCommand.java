package com.jflow.api.client.request.commands;

import com.jflow.api.client.dto.spec.FlowSpecDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SaveDraftFlowSpecCommand extends AuthCommand {
    private FlowSpecDTO flowSpec;
}
