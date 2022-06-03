package com.jflow.api.client.request.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Operation for a node of flow instance.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowInstanceNodeCommand extends FlowInstanceCommand {
    private String nodeId;
}
