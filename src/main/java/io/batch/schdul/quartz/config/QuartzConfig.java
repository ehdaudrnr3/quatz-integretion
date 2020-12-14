package io.batch.schdul.quartz.config;

import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final ApplicationContext ctx;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(new AutowiringSpringBeanJobFactory());
        factoryBean.setOverwriteExistingJobs(false);
        factoryBean.setAutoStartup(false);
        factoryBean.setDataSource(dataSource);
        factoryBean.setApplicationContext(ctx);
        factoryBean.setTransactionManager(transactionManager);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        return factoryBean;
    }

}
