package com.cubeia.translation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LANGUAGE_TRANSLATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageTranslationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "LANGUAGE_ID", nullable = false)
  private LanguageEntity language;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "TRANSLATION_ID", nullable = false)
  private TranslationEntity translation;

  private String translationValue;
}
