/*
package com.example.consumerservice.quartz;

import com.example.consumerservice.jobs.MessageProcessingJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail messageProcessingJobDetail() {
        return JobBuilder.newJob(MessageProcessingJob.class)
                .withIdentity("messageProcessingJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger messageProcessingJobTrigger(JobDetail messageProcessingJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(messageProcessingJobDetail)
                .withIdentity("messageProcessingJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // Every 30 seconds
                .build();
    }
}
*/
