package com.jflow.core.domain.flow.reference.instance.node;

import com.jflow.core.domain.engine.activity.NodeActivity;
import com.jflow.core.domain.flow.reference.instance.EdgeInstance;
import com.jflow.core.domain.flow.reference.instance.TaskInstance;
import com.jflow.core.domain.graph.Node;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public abstract class AbstractNodeInstance implements Node<EdgeInstance>, NodeActivity {

    private TaskInstance latestTask;

}
