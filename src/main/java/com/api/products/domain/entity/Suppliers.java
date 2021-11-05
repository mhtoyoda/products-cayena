package com.api.products.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Suppliers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "created_at", columnDefinition = "DATETIME")
  private LocalDateTime created;
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  private LocalDateTime updated;

}
