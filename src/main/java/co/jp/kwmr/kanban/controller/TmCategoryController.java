package co.jp.kwmr.kanban.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.jp.kwmr.kanban.entity.TmCategoryEntity;
import co.jp.kwmr.kanban.service.TmCategoryImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @CrossOrigin("http://localhost:5174")
@RequestMapping("/api/v1")
@RestController
public class TmCategoryController {

  private final TmCategoryImpl service;

  private TmCategoryController(TmCategoryImpl service) {
    this.service = service;
  }

  @GetMapping("/tm_category")
  public List<TmCategoryEntity> getCategories(
      @RequestParam(required = false, defaultValue = "id") String sortBy,
      @RequestParam(required = false, defaultValue = "ASC") String order) {
    List<TmCategoryEntity> tmCategoryList = service.getCategories(sortBy, order);
    return tmCategoryList;
  }

  @PostMapping("/tm_category")
  public void createCategory(@RequestBody TmCategoryEntity category) {
    service.createCategory(category);
  }

}
