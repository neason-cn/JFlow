package com.jflow.core.domain.flow.repository;

import com.jflow.common.exception.FlowException;
import com.jflow.core.domain.flow.aggregate.FlowSpec;
import com.jflow.core.domain.flow.repository.serializer.FlowSpecSerializer;
import com.jflow.infra.spi.storage.FlowSpecTunnel;
import com.jflow.infra.spi.storage.entity.FlowSpecEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.jflow.common.error.Errors.NO_FLOW_SPEC_MATCHES_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class FlowSpecRepository {

    private final FlowSpecSerializer flowSpecSerializer;
    private final FlowSpecTunnel flowSpecTunnel;

    public FlowSpec getById(String flowSpecId) {
        Optional<FlowSpecEntity> spec = flowSpecTunnel.getById(flowSpecId);
        if (!spec.isPresent()) {
            throw new FlowException(NO_FLOW_SPEC_MATCHES_ERROR, flowSpecId);
        }
        return flowSpecSerializer.deSerialize(spec.get());
    }

    public Optional<FlowSpec> getReleaseByCode(String code) {
        Optional<FlowSpecEntity> entity = flowSpecTunnel.getReleaseByCode(code);
        return entity.map(flowSpecSerializer::deSerialize);
    }

    public Optional<FlowSpec> getLatestVersionByCode(String code) {
        Optional<FlowSpecEntity> entity = flowSpecTunnel.getLatestVersionByCode(code);
        return entity.map(flowSpecSerializer::deSerialize);
    }

    public void save(FlowSpec flowSpec) {
        flowSpecTunnel.save(flowSpecSerializer.serialize(flowSpec));
    }

}
