package com.api.products.application.exception;

public class SuppliersNotFoundException extends RuntimeException {

  public SuppliersNotFoundException(String message) {
    super(message);
  }
}
