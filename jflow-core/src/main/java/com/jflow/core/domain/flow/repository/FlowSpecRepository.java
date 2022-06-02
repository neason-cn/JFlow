package com.jflow.core.domain.flow.repository;

import com.jflow.core.domain.flow.aggregate.FlowSpec;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowSpecRepository {

    public FlowSpec getById(String flowSpecId) {
        return null;
    }

    public FlowSpec getByCodeAndVersion(String code, String version) {
        return null;
    }

    public Optional<FlowSpec> getDraftByCode(String code) {
        return Optional.empty();
    }

    public Optional<FlowSpec> getReleaseByCode(String code) {
        return Optional.empty();
    }

    public Optional<FlowSpec> getLatestVersionByCode(String code) {
        return Optional.empty();
    }

    public void save(FlowSpec flowSpec) {

    }

}
