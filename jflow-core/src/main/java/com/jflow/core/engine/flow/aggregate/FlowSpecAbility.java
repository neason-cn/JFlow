package com.jflow.core.engine.flow.aggregate;

import com.jflow.core.engine.ctx.Context;

/**
 * @author neason
 * @since 0.0.1
 */
public interface FlowSpecAbility {

    /**
     * Release a draft flow spec, and the released one would be archived.
     */
    void release(Context ctx);

    /**
     * Archive a released flow spec.
     */
    void archive(Context ctx);

    void enableCron(Context ctx);

    void disableCron(Context ctx);

}
