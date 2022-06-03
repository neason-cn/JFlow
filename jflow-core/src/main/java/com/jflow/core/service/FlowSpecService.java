package com.jflow.core.service;

import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.auth.FlowUser;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecService {

    /**
     * Save a draft flow spec.
     *
     * @param draft draft spec vo
     * @param user  user who save this
     * @return flow spec unique id
     */
    String saveDraft(FlowSpecVO draft, FlowUser user);

    /**
     * Release a draft spec.
     *
     * @param flowSpecId flow spec id
     * @param user       user
     */
    void release(String flowSpecId, FlowUser user);

}
