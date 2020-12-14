package io.batch.schdul.batch.service;

import io.batch.schdul.batch.domain.BatchJob;
import io.batch.schdul.batch.repository.BatchJobRepository;
import io.batch.schdul.quartz.QuartzRunJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
@Service
@RequiredArgsConstructor
public class BatchJobService {

    private final BatchJobRepository repository;
    private final SchedulerFactoryBean schedulerFactoryBean;

    public void save(BatchJob batchJob) {
        BatchJob job = repository.save(batchJob);
    }

    public BatchJob findById(String jobId) {
        return repository.findById(jobId).orElseThrow(()->new IllegalArgumentException("not found"));
    }

    public void run(String jobId) {
        String identity = JobKey.createUniqueName("batch");
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(QuartzRunJob.class)
                .withIdentity(identity)
                .storeDurably()
                .build();
        jobDetail.getJobDataMap().put("id", jobId);

        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(identity)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        try {
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
