package com.example.producerservice.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Custom JobFactory implementation that enables Spring dependency injection in Quartz jobs.
 * This allows Quartz jobs to use Spring-managed beans (e.g., services).
 */
@Component
public class AutowiringSpringBeanJobFactory implements JobFactory {

    private final ApplicationContext applicationContext;

    /**
     * Constructor to inject the Spring ApplicationContext.
     *
     * @param applicationContext The Spring ApplicationContext.
     */
    @Autowired
    public AutowiringSpringBeanJobFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Creates a new instance of the job class and injects Spring-managed beans into it.
     *
     * @param bundle   The TriggerFiredBundle containing job details.
     * @param scheduler The Quartz scheduler.
     * @return A new job instance with Spring dependencies injected.
     * @throws JobExecutionException If an error occurs while creating the job instance.
     */
    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws JobExecutionException {
        // Get the job class from the job detail
        Class<?> jobClass = bundle.getJobDetail().getJobClass();

        try {
            // Retrieve the job instance from the Spring application context
            return (Job) applicationContext.getBean(jobClass);
        } catch (Exception e) {
            // Wrap any exceptions in a JobExecutionException
            throw new JobExecutionException("Error creating job instance for class: " + jobClass.getName(), e);
        }
    }
}
