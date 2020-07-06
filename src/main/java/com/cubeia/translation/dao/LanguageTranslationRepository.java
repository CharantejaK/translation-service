package com.cubeia.translation.dao;

import com.cubeia.translation.entity.LanguageTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LanguageTranslationRepository extends JpaRepository<LanguageTranslationEntity, Long> {
  LanguageTranslationEntity save(LanguageTranslationEntity entity);

  @Query("select languageTranslation from LanguageTranslationEntity languageTranslation "
      + " where languageTranslation.language.key = ?1 and languageTranslation.translation.key = ?2")
  LanguageTranslationEntity findByLanuageAndTranslation(String languageKey, String translationKey);
}
