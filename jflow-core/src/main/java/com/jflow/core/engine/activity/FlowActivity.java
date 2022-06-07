package com.jflow.core.engine.activity;

import com.jflow.core.engine.ctx.Context;

/**
 * All activities of a flow instance.
 *
 * @author neason
 * @since 0.0.1
 */
public interface FlowActivity {
    void onTerminate(Context context);
}
