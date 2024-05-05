package co.jp.kwmr.kanban.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jp.kwmr.kanban.entity.TmUsersEntity;
import co.jp.kwmr.kanban.service.TmUsersServiceImpl;

@RequestMapping("/api/v1")
@RestController
public class TmUsersController {

  private final TmUsersServiceImpl service;

  private TmUsersController(TmUsersServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/tm_users")
  public List<TmUsersEntity> getAllUsers() {
    List<TmUsersEntity> user = service.getAllUsers();
    return user;
  }

  @GetMapping("/tm_users/{userId}")
  public TmUsersEntity getUserById(@PathVariable Integer userId) {
    Optional<TmUsersEntity> userOption = service.getUserById(userId);
    TmUsersEntity user = userOption.get();
    return user;
  }
}
