package com.jflow.core.domain.engine.activity;

import com.jflow.core.domain.engine.Callback;
import com.jflow.core.domain.engine.Context;

/**
 * All activities of a node instance.
 *
 * @author neason
 * @since 0.0.1
 */
public interface TaskActivity {

    void onFire(Context ctx);

    void onCancel(Context ctx);

    void onCallback(Context ctx, Callback callback);

}
