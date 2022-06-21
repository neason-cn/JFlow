package com.jflow.core.domain.convertor;

import com.jflow.api.client.vo.spec.NodeSpecVO;
import com.jflow.core.engine.enums.type.NodeTypeEnum;
import com.jflow.core.engine.flow.spec.NodeSpec;
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
        if (null == vo) {
            return null;
        }
        NodeSpec nodeSpec = new NodeSpec();
        nodeSpec.setNodeId(vo.getNodeId());
        nodeSpec.setNodeName(vo.getNodeName());
        nodeSpec.setNodeType(NodeTypeEnum.of(vo.getNodeType()));
        nodeSpec.setWaitAll(new BooleanScript(vo.getWaitAll()));
        nodeSpec.setAutoFire(new BooleanScript(vo.getAutoFireScript()));
        nodeSpec.setAutoSkip(new BooleanScript(vo.getAutoSkipScript()));
        nodeSpec.setEnableSkip(new BooleanScript(vo.getEnableSkipScript()));
        nodeSpec.setEnableRetry(new BooleanScript(vo.getEnableRetryScript()));
        nodeSpec.setInterruptWhenSubmitFailed(new BooleanScript(vo.getInterruptWhenSubmitFailedScript()));
        nodeSpec.setInterruptWhenExecuteFailed(new BooleanScript(vo.getInterruptWhenExecuteFailedScript()));
        nodeSpec.setLabels(vo.getLabels());
        nodeSpec.setPreActions(actionSpecConvertor.batchConvertVO(vo.getPreActions()));
        nodeSpec.setTaskSpec(taskSpecConvertor.convert(vo.getTaskSpec()));
        nodeSpec.setPostActions(actionSpecConvertor.batchConvertVO(vo.getPostActions()));
        nodeSpec.setIncoming(new HashSet<>());
        nodeSpec.setOutgoing(new HashSet<>());
        return nodeSpec;
    }

    public NodeSpecVO convert(NodeSpec spec) {
        if (null == spec) {
            return null;
        }
        NodeSpecVO vo = new NodeSpecVO();
        vo.setNodeId(spec.getNodeId());
        vo.setNodeName(spec.getNodeName());
        vo.setNodeType(spec.getNodeType().getType());
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
        vo.setPreActions(actionSpecConvertor.batchConvertSpec(spec.getPreActions()));
        vo.setTaskSpec(taskSpecConvertor.convert(spec.getTaskSpec()));
        vo.setPostActions(actionSpecConvertor.batchConvertSpec(spec.getPostActions()));
        return vo;
    }

}
