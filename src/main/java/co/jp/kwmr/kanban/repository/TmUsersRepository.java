package co.jp.kwmr.kanban.repository;

import co.jp.kwmr.kanban.entity.TmUsersEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmUsersRepository extends CrudRepository<TmUsersEntity, Integer> {

}
