package com.github.maikoncarlos.pix.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
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

    public Pix() {
    }

    public Pix(UUID uuid, String keyType, String keyValue, String accountType, int agencyNumber, int accountNumber,
               String clientName, String clientLastName, LocalDateTime dateOfInclusion, LocalDateTime dateOfInactivation, boolean active) {
        this.uuid = uuid;
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.dateOfInclusion = dateOfInclusion;
        this.dateOfInactivation = dateOfInactivation;
        this.active = active;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(int agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public LocalDateTime getDateOfInclusion() {
        return dateOfInclusion;
    }

    public void setDateOfInclusion(LocalDateTime dateOfInclusion) {
        this.dateOfInclusion = dateOfInclusion;
    }

    public LocalDateTime getDateOfInactivation() {
        return dateOfInactivation;
    }

    public void setDateOfInactivation(LocalDateTime dateOfInactivation) {
        this.dateOfInactivation = dateOfInactivation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
