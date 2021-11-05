package com.api.products.application.validator;

import com.api.products.application.model.OperationEnum;
import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OperationSubSetValidator implements ConstraintValidator<OperationType, OperationEnum> {

  private OperationEnum[] subset;

  @Override
  public void initialize(OperationType constraint) {
    this.subset = constraint.anyOf();
  }

  @Override
  public boolean isValid(OperationEnum value,
      ConstraintValidatorContext constraintValidatorContext) {
    return value == null || Arrays.asList(subset).contains(value);
  }

}
