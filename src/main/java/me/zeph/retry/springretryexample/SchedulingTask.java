package me.zeph.retry.springretryexample;

import org.springframework.scheduling.annotation.Scheduled;

public class SchedulingTask {

  private RetryService retryService;

  public SchedulingTask(RetryService retryService) {
    this.retryService = retryService;
  }

  @Scheduled(fixedRate = 60000)
  public void work() {
    retryService.service();
  }

}
