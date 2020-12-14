package io.batch.schdul.quartz;

import io.batch.schdul.batch.domain.BatchJob;
import io.batch.schdul.batch.repository.BatchJobRepository;
import io.batch.schdul.util.BeanUtil;
import org.quartz.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Transactional
public class QuartzRunJob extends QuartzJobBean {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private BatchJobRepository batchJobRepository;

    @Autowired
    private ApplicationContext ctx;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobLocator jobLocator = (JobLocator) ctx.getBean(JobLocator.class);
            String jobId = jobExecutionContext.getJobDetail().getJobDataMap().get("id").toString();

            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("currentTime", System.currentTimeMillis())
                .toJobParameters();

            Job job = jobLocator.getJob(jobId);
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
