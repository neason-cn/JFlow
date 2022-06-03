package com.jflow.core.domain.flow.ability;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecAbility {

    /**
     * Release a draft flow spec, and the released one would be archived.
     *
     * @param userId someone who released
     */
    void release(String userId);

    /**
     * Archive a released flow spec.
     */
    void archive();

}
