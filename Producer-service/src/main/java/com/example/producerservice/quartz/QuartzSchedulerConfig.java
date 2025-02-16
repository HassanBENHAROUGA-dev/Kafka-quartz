/*
package com.example.producerservice.quartz;

import com.example.producerservice.jobs.AdjustmentJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzSchedulerConfig {
    @Bean
    public JobDetail adjustmentJobDetail() {
        return JobBuilder.newJob(AdjustmentJob.class)
                .withIdentity("adjustmentJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger adjustmentJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(adjustmentJobDetail())
                .withIdentity("adjustmentJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // Executes every 30 seconds
                .build();
    }

    @Bean(name = "quartzSchedulerFactoryBean")
    @Primary
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobDetails(adjustmentJobDetail());
        factory.setTriggers(adjustmentJobTrigger());
        return factory;
    }
}
*/
