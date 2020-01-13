package me.zeph.retry.springretryexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class UnstableController {

  @GetMapping("/unstable/{status}")
  public int unstableApi(@PathVariable int status) {
    if (INTERNAL_SERVER_ERROR.value() == status) {
      throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
    }
    if (UNAUTHORIZED.value() == status) {
      throw new ResponseStatusException(UNAUTHORIZED);
    }
    return status;
  }
}
