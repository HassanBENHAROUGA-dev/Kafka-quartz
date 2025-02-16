package com.example.producerservice.jobs;

import com.example.producerservice.model.AdjustmentSSY;
import com.example.producerservice.services.AdjustmentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope("prototype")// Ensures a new instance of the job is created for each execution
public class AdjustmentJob implements Job {
    private final AdjustmentService adjustmentService;

    @Value("${adjustment.job.edp-code:EDP123}")
    private String edpCodePrefix;

    @Value("${adjustment.job.days-to-look-back:1}")
    private int daysToLookBack;

    private static final AtomicLong idJobCounter = new AtomicLong(1);

    private static final Random random = new Random();

    public AdjustmentJob(AdjustmentService adjustmentService) {
        this.adjustmentService = adjustmentService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (adjustmentService == null) {
            throw new JobExecutionException("AdjustmentService is null!");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.minusDays(daysToLookBack);

        String edpCode = generateRandomEdpCode();
        long idJob = idJobCounter.getAndIncrement();

        AdjustmentSSY adjustmentSSY = new AdjustmentSSY();
        adjustmentSSY.setIdJob(idJob);
        adjustmentSSY.setEdpCode(edpCode);
        adjustmentSSY.setApplicationDateStart(startDate);
        adjustmentSSY.setApplicationDateEnd(now);
        adjustmentSSY.setPeriodStart(startDate);
        adjustmentSSY.setPeriodEnd(now);
        adjustmentSSY.setUpdateDate(now);
        adjustmentSSY.setReceivedDate(now);

        adjustmentService.sendAdjustment(adjustmentSSY);
    }

    private String generateRandomEdpCode() {
        int randomNumber = random.nextInt(90000) + 10000;
        return edpCodePrefix + randomNumber;
    }
}
