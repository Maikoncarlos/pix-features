package com.github.maikoncarlos.pix.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "pix")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pix {
    @Id
    private UUID uuid = UUID.randomUUID ();
    private String keyType;
    private String keyValue;
    private String accountType;
    private int agencyNumber;
    private int accountNumber;
    private String clientName;
    private String clientLastName;
    private LocalDateTime dateOfInclusion = LocalDateTime.now ();
    private LocalDateTime dateOfInactivation;
    private boolean active = true;

    public Pix(String keyType, String keyValue, String accountType, int agencyNumber, int accountNumber, String clientName, String clientLastName) {
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
    }
}