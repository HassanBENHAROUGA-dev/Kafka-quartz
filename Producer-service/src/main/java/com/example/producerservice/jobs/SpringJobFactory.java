package com.example.producerservice.quartz;

import org.quartz.Job;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringJobFactory implements JobFactory {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Job newJob(TriggerFiredBundle bundle, org.quartz.Scheduler scheduler) throws org.quartz.JobExecutionException {
        Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
        try {
            // Manually instantiate the job using Spring's context
            Job job = applicationContext.getAutowireCapableBeanFactory().createBean(jobClass);
            return job;
        } catch (Exception e) {
            throw new org.quartz.JobExecutionException("Error creating job instance", e);
        }
    }
}
