package com.github.maikoncarlos.pix.controller.dto;

public record PixRequestDTO(String keyType,
                            String keyValue,
                            String accountType,
                            int agencyNumber,
                            int accountNumber,
                            String clientName,
                            String clientLastName) {
}
