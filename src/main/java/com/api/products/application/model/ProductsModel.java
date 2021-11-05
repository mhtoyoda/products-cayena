package com.api.products.application.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsModel {

  private Long id;
  @NotNull
  private String name;
  @NotNull
  private BigDecimal price;
  @NotNull
  private Integer quantity;
  @NotNull
  private SuppliersModel suppliers;
}
