package com.example.producerservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdjustmentSSY {
    private Long idJob;
    private String edpCode;
    private LocalDateTime applicationDateStart;
    private LocalDateTime applicationDateEnd;
    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;
    private LocalDateTime updateDate;
    private LocalDateTime receivedDate;
}
