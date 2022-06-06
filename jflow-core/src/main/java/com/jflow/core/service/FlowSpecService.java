package com.jflow.core.service;

import com.jflow.api.client.vo.spec.FlowSpecVO;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecService {

    /**
     * Save a draft flow spec.
     *
     * @param draft draft spec vo
     * @return flow spec unique id
     */
    String saveDraft(FlowSpecVO draft);

    /**
     * Release a draft spec.
     *
     * @param flowSpecId flow spec id
     */
    void release(String flowSpecId);

}
