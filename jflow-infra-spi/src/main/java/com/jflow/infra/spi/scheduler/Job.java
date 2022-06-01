package com.jflow.infra.spi.scheduler;

/**
 * The job need to be scheduled.
 *
 * @author neason
 * @since 0.0.1
 */
public class Job {

    private String cron;

    private Runnable worker;

}
