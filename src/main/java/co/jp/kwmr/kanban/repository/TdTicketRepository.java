package co.jp.kwmr.kanban.repository;

import java.util.List;
import co.jp.kwmr.kanban.entity.TdTicketEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TdTicketRepository extends CrudRepository<TdTicketEntity, Integer> {

  List<TdTicketEntity> findAll(Sort sort);
}
