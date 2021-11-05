package com.api.products.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "unit_price")
  private BigDecimal price;
  private Integer quantity;
  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Suppliers suppliers;
  @Column(name = "created_at", columnDefinition = "DATETIME")
  private LocalDateTime created;
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  private LocalDateTime updated;

}
