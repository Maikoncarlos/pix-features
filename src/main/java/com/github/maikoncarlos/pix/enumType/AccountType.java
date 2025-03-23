package com.github.maikoncarlos.pix.enumType;

public enum AccountType {
    CORRENTE("corrente"),
    POUPANCA("poupanca");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
