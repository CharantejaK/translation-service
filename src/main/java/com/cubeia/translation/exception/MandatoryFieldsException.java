package com.cubeia.translation.exception;

public class MandatoryFieldsException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public MandatoryFieldsException(String errorMesage) {
    super(errorMesage) ;
  }

  public MandatoryFieldsException(Exception e) {
    super(e);
  }

}
