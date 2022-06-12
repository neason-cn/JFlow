package com.jflow.infra.impl.scheduler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.RunnableTask;
import com.jflow.common.exception.FlowException;
import com.jflow.infra.spi.scheduler.Job;
import com.jflow.infra.spi.scheduler.SchedulerSpi;
import org.springframework.stereotype.Component;

import static com.jflow.common.error.Errors.EMPTY_SCHEDULER_JOB_WORKER_ERROR;
import static com.jflow.common.error.Errors.NO_SUCH_SCHEDULER_JOB_ERROR;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
public class LocalSchedulerApi implements SchedulerSpi {

    @Override
    public String addJob(Job job) {
        if (null == job || null == job.getWorker()) {
            throw new FlowException(EMPTY_SCHEDULER_JOB_WORKER_ERROR);
        }
        String jobId = IdUtil.simpleUUID();
        CronUtil.schedule(jobId, job.getCron(), new RunnableTask(job.getWorker()));
        return jobId;
    }

    @Override
    public void deleteJob(String jobId) {
        boolean success = CronUtil.remove(jobId);
        if (!success) {
            throw new FlowException(NO_SUCH_SCHEDULER_JOB_ERROR, jobId);
        }
    }
}
