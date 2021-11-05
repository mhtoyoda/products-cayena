package com.api.products.application.model;

import com.api.products.application.validator.OperationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationModel {

  @NotNull
  private Long id;

  @OperationType(anyOf = {OperationEnum.DECREASE, OperationEnum.INCREASE})
  private OperationEnum operation;

  @NotNull
  @Positive
  private Integer quantity;
}
