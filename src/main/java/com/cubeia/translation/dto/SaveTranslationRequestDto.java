package com.cubeia.translation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTranslationRequestDto extends GenericRequestDto {

  private String translationValue;
}
