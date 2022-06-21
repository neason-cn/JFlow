package com.jflow.core.domain.convertor;

import com.jflow.api.client.vo.spec.ActionSpecVO;
import com.jflow.core.engine.enums.type.ActionTypeEnum;
import com.jflow.core.engine.flow.spec.ActionSpec;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ActionSpecConvertor {

    public ActionSpec convert(ActionSpecVO vo) {
        if (vo == null) {
            return null;
        }
        ActionSpec spec = new ActionSpec();
        spec.setName(vo.getName());
        spec.setActionType(ActionTypeEnum.of(vo.getActionType()));
        spec.setParams(vo.getParams());
        return spec;
    }

    public ActionSpecVO convert(ActionSpec spec) {
        if (spec == null) {
            return null;
        }
        ActionSpecVO vo = new ActionSpecVO();
        vo.setName(spec.getName());
        vo.setActionType(spec.getActionType().getType());
        vo.setParams(spec.getParams());
        return vo;
    }

    public Set<ActionSpec> batchConvertVO(Set<ActionSpecVO> actions) {
        if (CollectionUtils.isNotEmpty(actions)) {
            return actions.stream().map(this::convert).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    public Set<ActionSpecVO> batchConvertSpec(Set<ActionSpec> actions) {
        if (CollectionUtils.isNotEmpty(actions)) {
            return actions.stream().map(this::convert).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

}
