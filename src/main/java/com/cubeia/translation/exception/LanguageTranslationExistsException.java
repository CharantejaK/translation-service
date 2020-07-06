package com.cubeia.translation.exception;

public class LanguageTranslationExistsException extends BusinessException {
  private static final long serialVersionUID = 1L;

  public LanguageTranslationExistsException(String errorMesage) {
    super(errorMesage) ;
  }

  public LanguageTranslationExistsException(Exception e) {
    super(e);
  }

}

