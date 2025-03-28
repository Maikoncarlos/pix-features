package com.github.maikoncarlos.pix.controller.dto;

import com.github.maikoncarlos.pix.enumType.AccountType;
import com.github.maikoncarlos.pix.enumType.validation.ValueOfEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record PixUpdateRequestDTO(@NotBlank String id,
                                  @ValueOfEnum(enumClass = AccountType.class) @NotNull String accountType,
                                  @Max(9999) @NotNull int agencyNumber,
                                  @Max(99999999) @NotNull int accountNumber,
                                  @Length(min = 3, max = 30) @NotBlank String clientName,
                                  @Length(max = 45) String clientLastName) {
}
