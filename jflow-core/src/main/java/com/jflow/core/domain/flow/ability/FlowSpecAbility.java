package com.jflow.core.domain.flow.ability;

import com.jflow.core.domain.auth.FlowUser;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecAbility {

    /**
     * Release a draft flow spec, and the released one would be archived.
     *
     * @param user someone who released
     */
    void release(FlowUser user);

    /**
     * Archive a released flow spec.
     */
    void archive();

}
