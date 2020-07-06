package com.cubeia.translation.dao;

import com.cubeia.translation.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

  LanguageEntity save(LanguageEntity entity);

  LanguageEntity findByKey(String key);
}
