package com.jflow.core.service;

import com.jflow.api.client.request.commands.ReleaseFlowSpecCommand;
import com.jflow.api.client.request.commands.SaveDraftFlowSpecCommand;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecService {

    /**
     * Save a draft flow spec.
     *
     * @param command save command
     * @return flow spec unique id
     */
    String saveDraft(SaveDraftFlowSpecCommand command);

    void release(ReleaseFlowSpecCommand command);

}
