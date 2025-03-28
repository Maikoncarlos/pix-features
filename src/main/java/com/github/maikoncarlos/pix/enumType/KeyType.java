package com.github.maikoncarlos.pix.enumType;

import com.github.maikoncarlos.pix.exception.PixInvalidKeyValueException;
import com.github.maikoncarlos.pix.service.validation.*;

import java.util.Arrays;

import static java.lang.String.format;

public enum KeyType {
    ALEATORIO ("aleatorio") {
        @Override
        public void validated(String value) {
            if (!AleatorioValidator.isValid (value))
                throw new PixInvalidKeyValueException ("Key random invalid");
        }
    }, CELULAR ("celular") {
        @Override
        public void validated(String value) {
            if (!CelularValidator.isValid (value))
                throw new PixInvalidKeyValueException ("Celular invalid");

        }
    }, CNPJ ("cnpj") {
        @Override
        public void validated(String value) {
            if (!CNPJValidator.isValid (value))
                throw new PixInvalidKeyValueException ("CNPJ invalid");

        }
    }, CPF ("cpf") {
        @Override
        public void validated(String value) {
            if (!CPFValidator.isValid (value))
                throw new PixInvalidKeyValueException ("CPF invalid");

        }
    }, EMAIL ("email") {
        @Override
        public void validated(String value) {
            if (!EmailValidator.isValid (value))
                throw new PixInvalidKeyValueException ("Email invalid");
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
        return Arrays.stream (KeyType.values ()).filter (k -> k.value.equals (value)).findFirst ()
                .orElseThrow (() -> new IllegalArgumentException (format("valor do params keyType = %s é inválido!", value)));
    }

    public abstract void validated(String value);
}
