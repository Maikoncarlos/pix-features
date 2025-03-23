package com.github.maikoncarlos.pix.enumType;

public enum KeyType {
    ALEATORIO("aleatorio"),
    CELULAR("celular"),
    CNPJ("cnpj"),
    CPF("cpf"),
    EMAIL("email");

    private final String value;

    KeyType(String value) {
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
