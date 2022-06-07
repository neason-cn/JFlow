package com.jflow.core.domain.factory;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.engine.enums.status.FlowInstanceStatusEnum;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import com.jflow.core.engine.flow.instance.EdgeInstance;
import com.jflow.core.engine.flow.instance.node.AbstractNodeInstance;
import com.jflow.core.engine.graph.Graph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowInstanceFactory {

    private final NodeInstanceFactory nodeInstanceFactory;
    private final EdgeInstanceFactory edgeInstanceFactory;

    public FlowInstance create(FlowSpec spec, JSONObject input, String taskInstanceId) {
        FlowInstance instance = new FlowInstance(spec);
        instance.setFlowInstanceId(IdUtil.fastSimpleUUID());
        instance.setParentTaskInstanceId(taskInstanceId);
        instance.setStatus(FlowInstanceStatusEnum.INIT);
        instance.setInput(input);
        instance.setContext(input);
        instance.setOutput(new JSONObject());
        instance.setCreateAt(new Date());

        Set<AbstractNodeInstance> nodes = spec.getNodes().stream()
                .map(nodeInstanceFactory::create)
                .collect(Collectors.toSet());
        Set<EdgeInstance> edges = spec.getEdges().stream()
                .map(edgeInstanceFactory::create)
                .collect(Collectors.toSet());
        Graph.connect(nodes, edges);

        instance.setNodes(nodes);
        instance.setEdges(edges);
        return instance;
    }

}
