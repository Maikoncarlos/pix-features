package com.github.maikoncarlos.pix.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pix {

    @Id
    private UUID uuid = UUID.randomUUID ();
    private String keyType;
    private String keyValue;
    private String accountType;
    private Integer agencyNumber;
    private Integer accountNumber;
    private String clientName;
    private String clientLastName;
    private LocalDateTime dateOfInclusion = LocalDateTime.now ();
    private LocalDateTime dateOfInactivation;
}
