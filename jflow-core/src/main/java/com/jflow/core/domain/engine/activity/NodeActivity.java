package com.jflow.core.domain.engine.activity;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.engine.Callback;
import com.jflow.core.domain.engine.Context;
import com.jflow.core.domain.flow.reference.instance.EdgeInstance;

/**
 * All activities of a node instance.
 *
 * @author neason
 * @since 0.0.1
 */
public interface NodeActivity {

    void onSignal(Context ctx, EdgeInstance trigger);

    void onFire(Context ctx, JSONObject args);

    void onSkip(Context ctx, JSONObject args);

    void onRetry(Context ctx, JSONObject args);

    void onCancel(Context ctx, JSONObject args);

    void onCallback(Context ctx, Callback callback);

}
