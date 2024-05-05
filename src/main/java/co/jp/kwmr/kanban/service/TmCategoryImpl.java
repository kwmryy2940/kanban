package co.jp.kwmr.kanban.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import co.jp.kwmr.kanban.entity.TmCategoryEntity;
import co.jp.kwmr.kanban.repository.TmCategoryRepository;

@Service
public class TmCategoryImpl implements TmCategoryService {

  @Autowired
  TmCategoryRepository tmCategoryRepository;

  @Override
  public List<TmCategoryEntity> getCategories(String sortBy, String order) {
    Sort.Direction direction = order.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    Sort sort = Sort.by(direction, sortBy);

    Iterable<TmCategoryEntity> tmCategoryIterable = tmCategoryRepository.findAll(sort);
    List<TmCategoryEntity> tmCategoryList = new ArrayList<>();
    tmCategoryIterable.forEach(tmCategoryList::add);

    return tmCategoryList;
  }

  @Override
  public void createCategory(TmCategoryEntity category) {
    String categoryName = category.getCategoryName();
    Optional<TmCategoryEntity> category1 = tmCategoryRepository
        .findByCategoryName(categoryName);

    if (category1.isPresent()) {
      String errorMessage = String.format("登録済みのカテゴリです。[name=%s]", categoryName);
      throw new Error(errorMessage);
    }

    // システムから現在時刻を取得
    LocalDateTime now = LocalDateTime.now();

    // 登録日時、更新日時に現在時刻をセット
    category.setCreatedAt(now);
    category.setUpdatedAt(now);
    // DBに登録
    tmCategoryRepository.save(category);
  }

}
