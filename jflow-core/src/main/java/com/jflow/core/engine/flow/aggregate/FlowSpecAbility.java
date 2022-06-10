package com.jflow.core.engine.flow.aggregate;

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

    void enableCron(String cron);

    void disableCron();

}
