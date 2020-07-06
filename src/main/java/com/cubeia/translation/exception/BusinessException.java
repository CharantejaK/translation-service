package com.cubeia.translation.exception;

public class BusinessException extends RuntimeException {
  private static final long serialVersionUID = 3670096129928791764L;

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(Exception e) {
    super(e);
  }
}
