package com.github.maikoncarlos.pix.enumType;

import com.github.maikoncarlos.pix.exception.PixInvalidKeyValueException;
import com.github.maikoncarlos.pix.service.validation.CPFValidator;

import java.util.Arrays;

public enum KeyType {
    ALEATORIO ("aleatorio") {
        @Override
        public void validated(String value) {
        }
    }, CELULAR ("celular") {
        @Override
        public void validated(String value) {

        }
    }, CNPJ ("cnpj") {
        @Override
        public void validated(String value) {

        }
    }, CPF ("cpf") {
        @Override
        public void validated(String value) {
            final var cpfValidator = new CPFValidator ();

            if (!cpfValidator.isCPFValido (value))
                throw new PixInvalidKeyValueException ("CPF invalid");

        }
    }, EMAIL ("email") {
        @Override
        public void validated(String value) {

        }
    };

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

    public static KeyType getKeyType(final String value) {
        return Arrays.stream (KeyType.values ()).filter (k -> k.value.equals (value)).findFirst ().get ();
    }

    public abstract void validated(String value);
}
