package com.jflow.infra.spi.scheduler;

/**
 * @author neason
 * @since 0.0.1
 */
public interface SchedulerSpi {

    /**
     * Add a cron job.
     *
     * @param job job
     * @return job unique id
     */
    String addJob(Job job);

    /**
     * Delete a cron job.
     *
     * @param jobId job unique id
     * @return delete success or not
     */
    boolean deleteJob(String jobId);

}
