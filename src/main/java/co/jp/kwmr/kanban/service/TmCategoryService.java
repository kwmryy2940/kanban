package co.jp.kwmr.kanban.service;

import java.util.List;

import co.jp.kwmr.kanban.entity.TmCategoryEntity;

public interface TmCategoryService {
  public List<TmCategoryEntity> getCategories(String sortBy,String order);
  public void createCategory(TmCategoryEntity category);
}
