package com.api.products.application.model;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuppliersModel {

  private Long id;
  @NotNull
  private String name;

}
