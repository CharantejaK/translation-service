package com.cubeia.translation.controller;

import com.cubeia.translation.delegate.TranslationServiceDelegate;
import com.cubeia.translation.dto.GenericResponseDto;
import com.cubeia.translation.dto.GetTranslationResponseDto;
import com.cubeia.translation.dto.SaveTranslationRequestDto;
import com.cubeia.translation.exception.BusinessException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {

  public final static String SAVETRANSLATION_ENDPOINT = "/savetranslation";
  public final static String GETTRANSLATION_ENDPOINT = "/gettranslation";
  public final static String REQUESTPARAM_LANGUAGE = "language";
  public final static String REQUESTPARAM_TRANSLATIONKEY = "translationKey";

  @Autowired
  TranslationServiceDelegate translationDelegate;

  @PostMapping(value = SAVETRANSLATION_ENDPOINT, produces = (MediaType.APPLICATION_JSON_VALUE))
  public @ResponseBody
  ResponseEntity<GenericResponseDto> saveTranslation(@RequestBody SaveTranslationRequestDto saveRequest)
      throws BusinessException {
    GenericResponseDto response = new GenericResponseDto();
    translationDelegate.saveTranslation(saveRequest);
    response.setSuccess(Boolean.TRUE);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }

  @GetMapping(value = GETTRANSLATION_ENDPOINT, produces = (MediaType.APPLICATION_JSON_VALUE))
  public @ResponseBody
  ResponseEntity<GetTranslationResponseDto> fetchTranslation(
      @RequestParam(defaultValue = "", name = REQUESTPARAM_LANGUAGE) String language,
      @RequestParam(defaultValue = "", name = REQUESTPARAM_TRANSLATIONKEY) String translationKey) {
    GetTranslationResponseDto response = new GetTranslationResponseDto();
    Optional<String> translationValue = translationDelegate.fetchTransaltion(language, translationKey);
    response.setSuccess(Boolean.TRUE);
    response.setTranslationValue(translationValue.get());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }
}
