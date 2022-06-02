package com.jflow.core.domain.flow.factory;

import cn.hutool.core.util.IdUtil;
import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.core.domain.auth.FlowUser;
import com.jflow.core.domain.enums.status.FlowSpecStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.convertor.FlowSpecConvertor;
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
    public FlowSpec create(FlowSpecVO vo, int nextVersion, String userId) {
        FlowSpec flowSpec = new FlowSpec();

        // copy first
        flowSpecConvertor.copy(flowSpec, vo);

        // then set
        flowSpec.setFlowSpecId(IdUtil.fastSimpleUUID());
        flowSpec.setCreateBy(new FlowUser(userId));
        flowSpec.setCreateAt(new Date());
        flowSpec.setStatus(FlowSpecStatusEnum.DRAFT);
        flowSpec.setFlowSpecVersion(nextVersion);

        return flowSpec;
    }

}
