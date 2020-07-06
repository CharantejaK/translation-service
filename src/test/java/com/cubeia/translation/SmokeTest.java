package com.cubeia.translation;

import static org.assertj.core.api.Assertions.assertThat;

import com.cubeia.translation.controller.TranslationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

  @Autowired
  private TranslationController translationController;

  @Test
  public void contextLoads() {
    assertThat(translationController).isNotNull();
  }
}
