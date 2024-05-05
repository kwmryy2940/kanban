package co.jp.kwmr.kanban.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tm_category")
public class TmCategoryEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Integer id;

  @Column(name = "category_name")
  @Getter
  @Setter
  private String categoryName;

  @Column(name = "created_at")
  @Getter
  @Setter
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @Getter
  @Setter
  private LocalDateTime updatedAt;
}
