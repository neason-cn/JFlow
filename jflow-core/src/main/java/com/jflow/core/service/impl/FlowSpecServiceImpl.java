package com.jflow.core.service.impl;

import com.jflow.api.client.vo.spec.FlowSpecVO;
import com.jflow.common.config.TransactionExecutor;
import com.jflow.common.error.Errors;
import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.enums.status.FlowSpecStatusEnum;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.convertor.FlowSpecConvertor;
import com.jflow.core.domain.flow.factory.FlowSpecFactory;
import com.jflow.core.domain.flow.repository.FlowSpecRepository;
import com.jflow.core.service.FlowSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jflow.common.error.Errors.SYSTEM_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class FlowSpecServiceImpl implements FlowSpecService {

    private final FlowSpecFactory flowSpecFactory;
    private final FlowSpecConvertor flowSpecConvertor;
    private final FlowSpecRepository flowSpecRepository;
    private final TransactionExecutor transactionExecutor;


    @Override
    public String saveDraft(FlowSpecVO draft) {
        FlowSpec flowSpec = generateSpecToSave(draft);
        flowSpecRepository.save(flowSpec);
        return flowSpec.getFlowSpecId();
    }


    @Override
    public void release(String flowSpecId) {
        FlowSpec specToRelease = flowSpecRepository.getById(flowSpecId);

        Optional<FlowSpec> specToArchive = flowSpecRepository.getReleaseByCode(specToRelease.getFlowSpecCode());
        if (!specToArchive.isPresent()) {
            throw new FlowException(SYSTEM_ERROR, String.format("the code of %s has no spec version to archive.", specToRelease.getFlowSpecCode()));
        }

        // save two spec in transaction
        transactionExecutor.inTransaction(() -> {
            specToRelease.release();
            specToArchive.get().archive();
            flowSpecRepository.save(specToRelease);
            flowSpecRepository.save(specToArchive.get());
        });
    }

    private FlowSpec generateSpecToSave(FlowSpecVO flowSpecVO) {
        Optional<FlowSpec> latestSpecVersion = flowSpecRepository.getLatestVersionByCode(flowSpecVO.getFlowSpecCode());
        // there is a flow spec has the same code with the flowSpecVO
        if (latestSpecVersion.isPresent()) {
            FlowSpec oldSpec = latestSpecVersion.get();

            // update when the latest flow spec is still 'DRAFT'
            if (FlowSpecStatusEnum.DRAFT == oldSpec.getStatus()) {
                return flowSpecConvertor.merge(flowSpecVO, oldSpec);
            }

            if (FlowSpecStatusEnum.ARCHIVED == oldSpec.getStatus()) {
                throw new FlowException(Errors.ILLEGAL_FLOW_SPEC_STATUS_ERROR, oldSpec.getFlowSpecCode(), oldSpec.getFlowSpecId());
            }

            // create a new flow spec when the latest flow spec is already 'RELEASED'
            int nextVersion = oldSpec.getFlowSpecVersion() + 1;
            return flowSpecFactory.create(flowSpecVO, nextVersion);
        }

        // a new flow spec
        return flowSpecFactory.create(flowSpecVO, 1);
    }

}
