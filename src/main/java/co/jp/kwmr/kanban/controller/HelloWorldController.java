package co.jp.kwmr.kanban.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
  @GetMapping("/hello")
  public String getMsg() {
    return "Hello world!";
  }
}
