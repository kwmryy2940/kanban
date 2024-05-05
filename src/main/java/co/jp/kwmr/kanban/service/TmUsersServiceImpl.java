package co.jp.kwmr.kanban.service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.kwmr.kanban.entity.TmUsersEntity;
import co.jp.kwmr.kanban.repository.TmUsersRepository;

@Service
public class TmUsersServiceImpl implements TmUsersService {

  @Autowired
  TmUsersRepository tmUsersRepository;

  @Override
  public List<TmUsersEntity> getAllUsers() {
    Iterable<TmUsersEntity> userIterable = tmUsersRepository.findAll();
    List<TmUsersEntity> user = new ArrayList<>();
    userIterable.forEach(user::add);

    return user;
  }

  @Override
  public Optional<TmUsersEntity> getUserById(Integer userId) {
    Optional<TmUsersEntity> user = tmUsersRepository.findById(userId);
    return user;
  }

}
