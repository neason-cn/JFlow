package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.NodeSpecVO;
import com.jflow.core.domain.flow.reference.spec.NodeSpec;
import com.jflow.infra.spi.script.type.BooleanScript;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class NodeSpecConvertor {

    private final TaskSpecConvertor taskSpecConvertor;
    private final ActionSpecConvertor actionSpecConvertor;

    public NodeSpec convert(NodeSpecVO vo) {
        NodeSpec nodeSpec = new NodeSpec();
        nodeSpec.setNodeId(vo.getNodeId());
        nodeSpec.setNodeName(vo.getNodeName());
        nodeSpec.setWaitAll(new BooleanScript(vo.getWaitAll()));
        nodeSpec.setAutoFire(new BooleanScript(vo.getAutoFireScript()));
        nodeSpec.setAutoSkip(new BooleanScript(vo.getAutoSkipScript()));
        nodeSpec.setEnableSkip(new BooleanScript(vo.getEnableSkipScript()));
        nodeSpec.setEnableRetry(new BooleanScript(vo.getEnableRetryScript()));
        nodeSpec.setInterruptWhenSubmitFailed(new BooleanScript(vo.getInterruptWhenSubmitFailedScript()));
        nodeSpec.setInterruptWhenExecuteFailed(new BooleanScript(vo.getInterruptWhenExecuteFailedScript()));
        nodeSpec.setLabels(vo.getLabels());
        nodeSpec.setBefore(actionSpecConvertor.convert(vo.getBeforeTaskAction()));
        nodeSpec.setTaskSpec(taskSpecConvertor.convert(vo.getTaskSpec()));
        nodeSpec.setAfter(actionSpecConvertor.convert(vo.getAfterTaskAction()));
        nodeSpec.setIncoming(new HashSet<>());
        nodeSpec.setOutgoing(new HashSet<>());
        return nodeSpec;
    }

    public NodeSpecVO convert(NodeSpec spec) {
        NodeSpecVO vo = new NodeSpecVO();
        vo.setNodeId(spec.getNodeId());
        vo.setNodeName(vo.getNodeName());
        vo.setWaitAll(spec.getWaitAll().getContent());
        vo.setAutoFireScript(spec.getAutoFire() != null ? spec.getAutoFire().getContent() : null);
        vo.setAutoSkipScript(spec.getAutoSkip() != null ? spec.getAutoSkip().getContent() : null);
        vo.setEnableSkipScript(spec.getEnableSkip() != null ? spec.getEnableSkip().getContent() : null);
        vo.setEnableRetryScript(spec.getEnableRetry() != null ? spec.getEnableRetry().getContent() : null);
        vo.setInterruptWhenSubmitFailedScript(spec.getInterruptWhenSubmitFailed() != null ?
                spec.getInterruptWhenSubmitFailed().getContent() : null);
        vo.setInterruptWhenExecuteFailedScript(spec.getInterruptWhenExecuteFailed() != null ?
                spec.getInterruptWhenExecuteFailed().getContent() : null);
        vo.setLabels(spec.getLabels());
        vo.setBeforeTaskAction(actionSpecConvertor.convert(spec.getBefore()));
        vo.setTaskSpec(taskSpecConvertor.convert(spec.getTaskSpec()));
        vo.setAfterTaskAction(actionSpecConvertor.convert(spec.getAfter()));
        return vo;
    }

}
