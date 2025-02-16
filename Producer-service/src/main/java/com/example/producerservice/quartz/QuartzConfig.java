package com.example.producerservice.quartz;

import com.example.producerservice.jobs.AdjustmentJob;
import com.example.producerservice.jobs.AutowiringSpringBeanJobFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    private final ApplicationContext applicationContext;

    @Value("${adjustment.job.cron:0/30 * * * * ?}") // Default cron expression: every 30 seconds
    private String cronExpression;

    /**
     * Constructor to inject the Spring ApplicationContext.
     *
     * @param applicationContext The Spring ApplicationContext.
     */
    public QuartzConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Defines the job detail for the AdjustmentJob.
     *
     * @return JobDetail instance for the AdjustmentJob.
     */
    @Bean
    public JobDetail adjustmentJobDetail() {
        return JobBuilder.newJob(AdjustmentJob.class)
                .withIdentity("adjustmentJob") // Unique identifier for the job
                .storeDurably() // Persist the job even if no triggers are associated
                .build();
    }

    /**
     * Defines the trigger for the AdjustmentJob.
     *
     * @param adjustmentJobDetail The JobDetail instance for the AdjustmentJob.
     * @return Trigger instance for the AdjustmentJob.
     */
    @Bean
    public Trigger adjustmentJobTrigger(JobDetail adjustmentJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(adjustmentJobDetail) // Associate the trigger with the job
                .withIdentity("adjustmentJobTrigger") // Unique identifier for the trigger
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)) // Use the cron expression
                .build();
    }

    /**
     * Configures the SchedulerFactoryBean with the job and trigger.
     *
     * @param adjustmentJobDetail The JobDetail instance for the AdjustmentJob.
     * @param adjustmentJobTrigger The Trigger instance for the AdjustmentJob.
     * @return SchedulerFactoryBean instance.
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobDetail adjustmentJobDetail, Trigger adjustmentJobTrigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

        // Use a custom job factory to enable Spring dependency injection in Quartz jobs
        factoryBean.setJobFactory(new AutowiringSpringBeanJobFactory(applicationContext));

        // Register the job and trigger
        factoryBean.setJobDetails(adjustmentJobDetail);
        factoryBean.setTriggers(adjustmentJobTrigger);

        return factoryBean;
    }

    /**
     * Starts the Quartz scheduler.
     *
     * @param schedulerFactoryBean The SchedulerFactoryBean instance.
     * @return Scheduler instance.
     * @throws Exception If the scheduler fails to start.
     */
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start(); // Start the scheduler
        return scheduler;
    }
}
