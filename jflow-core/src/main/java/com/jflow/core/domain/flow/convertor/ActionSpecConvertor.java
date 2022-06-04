package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.ActionSpecVO;
import com.jflow.core.domain.enums.type.ActionTypeEnum;
import com.jflow.core.domain.flow.reference.spec.action.ActionSpec;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class ActionSpecConvertor {

    public ActionSpec convert(ActionSpecVO vo) {
        ActionSpec spec = new ActionSpec();
        spec.setActionType(ActionTypeEnum.of(vo.getActionType()));
        spec.setParams(vo.getParams());
        return spec;
    }

    public ActionSpecVO convert(ActionSpec spec) {
        ActionSpecVO vo = new ActionSpecVO();
        vo.setActionType(spec.getActionType().getType());
        vo.setParams(spec.getParams());
        return vo;
    }

}
