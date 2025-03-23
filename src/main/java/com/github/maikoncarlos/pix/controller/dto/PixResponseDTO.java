package com.github.maikoncarlos.pix.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public record PixResponseDTO(UUID uuid,
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
