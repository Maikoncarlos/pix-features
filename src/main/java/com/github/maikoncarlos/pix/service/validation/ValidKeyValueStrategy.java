package com.github.maikoncarlos.pix.service.validation;

import com.github.maikoncarlos.pix.enumType.KeyType;

public class ValidKeyValueStrategy {

    private final KeyType keyType;

    public ValidKeyValueStrategy(KeyType keyType) {
        this.keyType = keyType;
    }

    public void execute(final String value) {
        keyType.validated (value);
    }

}
