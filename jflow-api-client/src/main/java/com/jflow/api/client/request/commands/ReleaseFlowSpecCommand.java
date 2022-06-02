package com.jflow.api.client.request.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReleaseFlowSpecCommand extends AuthCommand {
    private String flowSpecId;
}
