package me.zeph.retry.springretryexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulingConfig {

  @Autowired
  private RetryService retryService;

  @Bean
  public SchedulingTask schedulingTask(){
    return new SchedulingTask(retryService);
  }
}
