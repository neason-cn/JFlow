package com.jflow.infra.impl.scheduler;

import com.jflow.infra.spi.scheduler.Job;
import com.jflow.infra.spi.scheduler.SchedulerSpi;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class SchedulerApi implements SchedulerSpi {
    @Override
    public String addJob(Job job) {
        return null;
    }

    @Override
    public boolean deleteJob(String jobId) {
        return false;
    }
}
