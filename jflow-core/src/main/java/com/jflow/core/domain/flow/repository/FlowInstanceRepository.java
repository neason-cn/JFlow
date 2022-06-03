package com.jflow.core.domain.flow.repository;

import com.jflow.core.domain.flow.aggregate.FlowInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class FlowInstanceRepository {

    public void save(FlowInstance flowInstance) {

    }

    public FlowInstance getById(String flowInstanceId) {
        return null;
    }

    public Optional<List<FlowInstance>> getAllInstanceOfSpec(String flowSpecCode) {
        return Optional.empty();
    }

    public FlowInstance getByTaskId(String taskInstanceId) {
        return null;
    }

}
