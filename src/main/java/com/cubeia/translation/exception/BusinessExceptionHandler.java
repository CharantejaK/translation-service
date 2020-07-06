package com.cubeia.translation.exception;

import com.cubeia.translation.dto.GenericResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class BusinessExceptionHandler
    extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<GenericResponseDto> handlevalidations(
      BusinessException ex, WebRequest request) {
    GenericResponseDto response = new GenericResponseDto();
    String bodyOfResponse = ex.getMessage();
    response.setSuccess(Boolean.FALSE);
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    response.setErrors(errors);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }


  @ExceptionHandler(Exception.class)
  protected ResponseEntity<GenericResponseDto> internalError(
      Exception ex, WebRequest request) {
    GenericResponseDto response = new GenericResponseDto();
    String bodyOfResponse = ex.getMessage();
    response.setSuccess(Boolean.FALSE);
    List<String> errors = new ArrayList<>();
    errors.add("Internal Error");
    response.setErrors(errors);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
