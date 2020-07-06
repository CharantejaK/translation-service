package com.cubeia.translation;

import static com.cubeia.translation.controller.TranslationController.REQUESTPARAM_LANGUAGE;
import static com.cubeia.translation.controller.TranslationController.REQUESTPARAM_TRANSLATIONKEY;
import static com.cubeia.translation.controller.TranslationController.SAVETRANSLATION_ENDPOINT;
import static com.cubeia.translation.controller.TranslationController.GETTRANSLATION_ENDPOINT;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.cubeia.translation.controller.TranslationController;
import com.cubeia.translation.delegate.TranslationServiceDelegate;
import com.cubeia.translation.dto.SaveTranslationRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TranslationController.class)
public class TranslationControllerMockTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private TranslationServiceDelegate translationServiceDelegate;

  @Test
  public void saveTranslationTest() throws Exception {
    SaveTranslationRequestDto requestDto = new SaveTranslationRequestDto();
    requestDto.setTranslationValue("Buy Chips");
    requestDto.setLanguage("en");
    requestDto.setTranslationKey("buy-chips");
    this.mockMvc.perform(post(SAVETRANSLATION_ENDPOINT)
        .content(objectMapper.writeValueAsString(requestDto))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("success").value(Boolean.TRUE.toString()));
  }

  @Test
  public void fetchTranslationTest() throws Exception {
    String language = "en";
    String translationKey = "buy-chips";
    String translationValue = "Buy Chips";
    when(translationServiceDelegate.fetchTransaltion(language, translationKey)).thenReturn(Optional.of(translationValue));
    SaveTranslationRequestDto requestDto = new SaveTranslationRequestDto();
    this.mockMvc.perform(get(GETTRANSLATION_ENDPOINT)
        .param(REQUESTPARAM_LANGUAGE, language).param(REQUESTPARAM_TRANSLATIONKEY, translationKey)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("translationValue").value(translationValue));
  }
}
