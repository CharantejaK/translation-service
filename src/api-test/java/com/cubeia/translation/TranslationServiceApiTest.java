package com.cubeia.translation;

import static com.cubeia.translation.controller.TranslationController.GETTRANSLATION_ENDPOINT;
import static com.cubeia.translation.controller.TranslationController.REQUESTPARAM_LANGUAGE;
import static com.cubeia.translation.controller.TranslationController.REQUESTPARAM_TRANSLATIONKEY;
import static com.cubeia.translation.controller.TranslationController.SAVETRANSLATION_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cubeia.translation.dto.SaveTranslationRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


/**
 * Testing the controller without starting the server
 * @See
 * https://spring.io/guides/gs/testing-web/
 */

@SpringBootTest
@AutoConfigureMockMvc
public class TranslationServiceApiTest {

  private static final String LANGUAGE = "en";
  private static final String TRANSLATIONKEY = "buy-chips";
  private static final String TRANSLATION_VALUE= "Buy Chips";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  public void saveTranslationTest() throws Exception {
          getSaveRequest("en", "buy-chips", "Buy Chips")
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("success").value(Boolean.TRUE.toString()));
  }

  @Test
  public void fetchTranslationTest() throws Exception {
    SaveTranslationRequestDto requestDto = new SaveTranslationRequestDto();
    getSaveRequest("en", "get-chocos", "Get Chocos");
    this.mockMvc.perform(get(GETTRANSLATION_ENDPOINT)
        .param(REQUESTPARAM_LANGUAGE, "en").param(REQUESTPARAM_TRANSLATIONKEY, "get-chocos")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("translationValue").value("Get Chocos"));
  }


  private ResultActions getSaveRequest(String language, String key, String value) throws  Exception {
    SaveTranslationRequestDto requestDto = new SaveTranslationRequestDto();
    requestDto.setTranslationValue(value);
    requestDto.setLanguage(language);
    requestDto.setTranslationKey(key);
    return this.mockMvc.perform(post(SAVETRANSLATION_ENDPOINT)
        .content(objectMapper.writeValueAsString(requestDto))
        .contentType(MediaType.APPLICATION_JSON));
  }

}
