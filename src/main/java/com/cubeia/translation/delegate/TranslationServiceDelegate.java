package com.cubeia.translation.delegate;

import com.cubeia.translation.dao.LanguageRepository;
import com.cubeia.translation.dao.LanguageTranslationRepository;
import com.cubeia.translation.dao.TranslationRepository;
import com.cubeia.translation.dto.SaveTranslationRequestDto;
import com.cubeia.translation.entity.LanguageEntity;
import com.cubeia.translation.entity.LanguageTranslationEntity;
import com.cubeia.translation.entity.TranslationEntity;
import com.cubeia.translation.exception.BusinessException;
import com.cubeia.translation.exception.LanguageTranslationExistsException;
import com.cubeia.translation.exception.MandatoryFieldsException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class TranslationServiceDelegate {

  @Autowired
  LanguageRepository languageRepository;

  @Autowired
  TranslationRepository translationRepository;

  @Autowired
  LanguageTranslationRepository languageTranslationRepository;

  @Autowired
  Environment env;

  public void saveTranslation(SaveTranslationRequestDto saverequest) throws BusinessException {
    String languageKey = Optional.ofNullable(saverequest.getLanguage()).orElse("").trim().toLowerCase();
    String translationKey = Optional.ofNullable(saverequest.getTranslationKey()).orElse("").trim();
    String translationValue = Optional.ofNullable(saverequest.getTranslationValue()).orElse("").trim();

    if (languageKey.isEmpty() || translationKey.isEmpty() || translationValue.isEmpty()) {
      throw new MandatoryFieldsException(env.getProperty("translation.error.mandatoryfields"));
    }
    LanguageEntity language = languageRepository.findByKey(languageKey);

    if (language == null) {
      language = new LanguageEntity();
      language.setKey(languageKey);
      languageRepository.save(language);
    }

    TranslationEntity translation = translationRepository.findByKey(translationKey);

    if (translation == null) {
      translation = new TranslationEntity();
      translation.setKey(translationKey);
      translationRepository.save(translation);
    }

    LanguageTranslationEntity languageTranslation = languageTranslationRepository
        .findByLanuageAndTranslation(languageKey, translationKey);

    if (languageTranslation != null) {
      throw new LanguageTranslationExistsException(env.getProperty("translation.error.translationalreadyexists"));
    }

    languageTranslation = new LanguageTranslationEntity();
    languageTranslation.setLanguage(language);
    languageTranslation.setTranslation(translation);
    languageTranslation.setTranslationValue(translationValue);

    languageTranslationRepository.save(languageTranslation);
  }

  public Optional<String> fetchTransaltion(String language, String translationKey) {
    LanguageTranslationEntity entity = languageTranslationRepository
        .findByLanuageAndTranslation(language, translationKey);
    if (entity != null) {
      return Optional.ofNullable(entity.getTranslationValue());
    }
    return Optional.of("");
  }
}
