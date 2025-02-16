package com.example.consumerservice.model;

import java.time.LocalDateTime;

public class AdjustmentSSYKafka {

  private Long idJob;
  private String edpCode;
  private LocalDateTime applicationDateStart;
  private LocalDateTime applicationDateEnd;
  private LocalDateTime periodStart;
  private LocalDateTime periodEnd;
  private LocalDateTime updateDate;
  private LocalDateTime receivedDate;

  // Getters and setters for all fields

  public Long getIdJob() {
    return idJob;
  }

  public void setIdJob(Long idJob) {
    this.idJob = idJob;
  }

  public String getEdpCode() {
    return edpCode;
  }

  public void setEdpCode(String edpCode) {
    this.edpCode = edpCode;
  }

  public LocalDateTime getApplicationDateStart() {
    return applicationDateStart;
  }

  public void setApplicationDateStart(LocalDateTime applicationDateStart) {
    this.applicationDateStart = applicationDateStart;
  }

  public LocalDateTime getApplicationDateEnd() {
    return applicationDateEnd;
  }

  public void setApplicationDateEnd(LocalDateTime applicationDateEnd) {
    this.applicationDateEnd = applicationDateEnd;
  }

  public LocalDateTime getPeriodStart() {
    return periodStart;
  }

  public void setPeriodStart(LocalDateTime periodStart) {
    this.periodStart = periodStart;
  }

  public LocalDateTime getPeriodEnd() {
    return periodEnd;
  }

  public void setPeriodEnd(LocalDateTime periodEnd) {
    this.periodEnd = periodEnd;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  public LocalDateTime getReceivedDate() {
    return receivedDate;
  }

  public void setReceivedDate(LocalDateTime receivedDate) {
    this.receivedDate = receivedDate;
  }
}
