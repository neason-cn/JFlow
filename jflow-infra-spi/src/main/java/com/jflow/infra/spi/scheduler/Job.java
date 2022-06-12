package com.jflow.infra.spi.scheduler;

import lombok.Data;

/**
 * The job need to be scheduled.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class Job {

    private String cron;

    private Runnable worker;

}
