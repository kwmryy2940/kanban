package co.jp.kwmr.kanban.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "td_ticket")
public class TdTicketEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Integer id;

  @Column(name = "user_id")
  @Getter
  @Setter
  private Integer userId;

  @Column(name = "category_id")
  @Getter
  @Setter
  private Integer categoryId;

  @Column(name = "status")
  @Getter
  @Setter
  private Integer status;

  @Column(name = "ticket_title")
  @Getter
  @Setter
  private String ticketTitle;

  @Column(name = "ticket_detail")
  @Getter
  @Setter
  private String ticketDetail;

  @Column(name = "created_at")
  @Getter
  @Setter
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @Getter
  @Setter
  private LocalDateTime updatedAt;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  private TmUsersEntity user;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
  private TmCategoryEntity category;

}
