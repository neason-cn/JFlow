package com.jflow.core.engine.activity;

import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.ctx.Context;

/**
 * All activities of an action instance.
 *
 * @author neason
 * @since 0.0.1
 */
public interface ActionActivity {

    ActionResponse onExecute(Context ctx);

}
