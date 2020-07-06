package com.cubeia.translation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequestDto {

  private String language;
  private String translationKey;
}
