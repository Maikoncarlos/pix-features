package com.github.maikoncarlos.pix.service.validation;

import java.util.regex.Pattern;

public class AleatorioValidator {

    private static final String ALFANUMERICO_REGEX = "^[a-zA-Z0-9]{36}$";

    public static boolean isValid(String input) {
        if (input == null) {
            return false;
        }
        return Pattern.compile (ALFANUMERICO_REGEX).matcher (input).matches ();
    }
}
