package com.github.maikoncarlos.pix.service.validation;

import java.util.regex.Pattern;

public class CelularValidator {
    public static boolean isValid(String value) {
        final String CELULAR_REGEX = "^\\+\\d{1,3}\\d{2}9\\d{8}$";

        if (value == null) {
            return false;
        }
        return Pattern.compile (CELULAR_REGEX).matcher (value).matches ();
    }
}
