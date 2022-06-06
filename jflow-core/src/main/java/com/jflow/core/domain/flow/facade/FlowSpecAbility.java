package com.jflow.core.domain.flow.facade;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecAbility {

    /**
     * Release a draft flow spec, and the released one would be archived.
     */
    void release();

    /**
     * Archive a released flow spec.
     */
    void archive();

}
