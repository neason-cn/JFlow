package com.jflow.infra.impl.scheduler;

import cn.hutool.core.util.IdUtil;
import com.jflow.common.exception.FlowException;
import com.jflow.infra.spi.scheduler.Job;
import com.jflow.infra.spi.scheduler.SchedulerSpi;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import static com.jflow.common.error.Errors.*;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class SpringSchedulerApi implements SchedulerSpi {

    private ThreadPoolTaskScheduler scheduler;
    private final ConcurrentHashMap<String, ScheduledFuture<?>> jobStorage = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(100);
        scheduler.setThreadNamePrefix("jflow-scheduler-");
        scheduler.setRemoveOnCancelPolicy(true);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.afterPropertiesSet();
    }

    @Override
    public String addJob(Job job) {
        if (null == job || null == job.getWorker()) {
            throw new FlowException(EMPTY_SCHEDULER_JOB_WORKER_ERROR);
        }

        ScheduledFuture<?> future;
        try {
            future = scheduler.schedule(job.getWorker(), new CronTrigger(job.getCron()));
        } catch (Exception e) {
            throw new FlowException(CREATE_SCHEDULER_JOB_ERROR, e.getMessage());
        }

        if (null == future) {
            throw new FlowException(CREATE_SCHEDULER_JOB_ERROR, "job future is null");
        }

        String jodId = IdUtil.simpleUUID();
        jobStorage.put(jodId, future);
        return jodId;
    }

    @Override
    public void deleteJob(String jobId) {
        ScheduledFuture<?> future = jobStorage.get(jobId);
        if (null == future) {
            throw new FlowException(NO_SUCH_SCHEDULER_JOB_ERROR, jobId);
        }
        future.cancel(true);
    }
}
