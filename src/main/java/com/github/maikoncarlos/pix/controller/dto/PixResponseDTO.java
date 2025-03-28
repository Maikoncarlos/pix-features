package com.github.maikoncarlos.pix.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
public record PixResponseDTO(UUID id,
                             String keyType,
                             String keyValue,
                             String accountType,
                             int agencyNumber,
                             int accountNumber,
                             String clientName,
                             @JsonInclude(NON_NULL)
                             String clientLastName,
                             LocalDateTime dateOfInclusion,
                             @JsonInclude(NON_NULL)
                             LocalDateTime dateOfInactivation) {

}
