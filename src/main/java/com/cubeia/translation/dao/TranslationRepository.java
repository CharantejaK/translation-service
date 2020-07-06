package com.cubeia.translation.dao;

import com.cubeia.translation.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<TranslationEntity, Long> {

  TranslationEntity save(TranslationEntity entity);

  TranslationEntity findByKey(String key);
}
