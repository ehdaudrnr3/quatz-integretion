package io.batch.schdul.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class jobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step stepTest(){
        return stepBuilderFactory.get("stepTest")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("StepOne tasklet");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job jobTest() {
        return jobBuilderFactory.get("jobTest")
                .start(stepTest())
                .build();
    }
}
