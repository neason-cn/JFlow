package com.jflow.core.domain.engine.activity;

import com.jflow.core.domain.engine.Context;

/**
 * All activities of an action instance.
 *
 * @author neason
 * @since 0.0.1
 */
public interface ActionActivity {

    void onExecute(Context ctx);

}
