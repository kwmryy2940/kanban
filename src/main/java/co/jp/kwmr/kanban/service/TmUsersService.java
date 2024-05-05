package co.jp.kwmr.kanban.service;

import java.util.Optional;
import java.util.List;

import co.jp.kwmr.kanban.entity.TmUsersEntity;

public interface TmUsersService {
  public List<TmUsersEntity> getAllUsers();
  public Optional<TmUsersEntity> getUserById(Integer userId);
}
