package com.github.maikoncarlos.pix.service.validation;

import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isValid(String value) {

        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";

        if (value == null|| value.length() > 77) {
            return false;
        }
        return Pattern.compile (EMAIL_REGEX).matcher (value).matches ();
    }
}
