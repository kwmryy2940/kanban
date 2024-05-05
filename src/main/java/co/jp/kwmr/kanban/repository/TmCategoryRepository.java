package co.jp.kwmr.kanban.repository;

import java.util.List;
import java.util.Optional;
import co.jp.kwmr.kanban.entity.TmCategoryEntity;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmCategoryRepository extends CrudRepository<TmCategoryEntity, Integer> {
  List<TmCategoryEntity> findAll(Sort sort);

  Optional<TmCategoryEntity> findByCategoryName(String categoryName);
}
