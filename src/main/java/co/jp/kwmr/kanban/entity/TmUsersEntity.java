package co.jp.kwmr.kanban.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "tm_users")
public class TmUsersEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Integer id;

  @Column(name = "user_name")
  @Getter
  @Setter
  private String userName;

  @Column(name = "user_email")
  @Getter
  @Setter
  private String userEmail;

  @Column(name = "password")
  @Getter
  @Setter
  private String password;

  @Column(name = "admin_flg")
  @Getter
  @Setter
  private Integer adminFlg;

  @Column(name = "created_at")
  @Getter
  @Setter
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @Getter
  @Setter
  private LocalDateTime updatedAt;
}
