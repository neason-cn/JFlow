package com.jflow.api.client.request.commands;

import com.jflow.api.client.vo.spec.FlowSpecVO;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class SaveDraftFlowSpecCommand {
    private FlowSpecVO flowSpec;
}
