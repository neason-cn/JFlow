package com.jflow.core.domain.factory;

import cn.hutool.core.util.IdUtil;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.convertor.FlowSpecConvertor;
import com.jflow.core.engine.enums.status.FlowSpecStatusEnum;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowSpecFactory {

    private final FlowSpecConvertor flowSpecConvertor;
    public FlowSpec create(FlowSpecVO vo, int nextVersion) {
        FlowSpec flowSpec = new FlowSpec();

        // copy first
        flowSpecConvertor.copyPropertiesFromVO(flowSpec, vo);

        // then set
        flowSpec.setFlowSpecId(IdUtil.fastSimpleUUID());
        flowSpec.setCreateAt(new Date());
        flowSpec.setStatus(FlowSpecStatusEnum.DRAFT);
        flowSpec.setFlowSpecVersion(nextVersion);

        return flowSpec;
    }

}
