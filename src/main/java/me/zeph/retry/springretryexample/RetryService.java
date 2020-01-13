package me.zeph.retry.springretryexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RetryService {

  private static final int DELAY_TIME = 5000;

  @Autowired
  private RestTemplate restTemplate;

  @Retryable(value = RemoteAccessException.class, backoff = @Backoff(DELAY_TIME))
  public void service() {
    try {
      ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/unstable/500", String.class);
    } catch (Exception e) {
      System.out.println("Try get unstable api failed");
      throw new RemoteAccessException("500", e);
    }
  }

  @Recover
  public void recover(RemoteAccessException e) {
    ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/unstable/200", String.class);
    System.out.println(String.format("Response is %s", responseEntity.getBody()));
  }

}