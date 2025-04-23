package com.github.maikoncarlos.pix.fatory;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;

import static com.github.maikoncarlos.pix.util.MocksUtil.*;

public class PixRequestDTOFactory {

    public static PixRequestDTO success() {
        return PixRequestDTO.builder ()
                .keyType (KEY_TYPE_CPF)
                .keyValue (KEY_VALUE_CPF)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENCY_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static PixRequestDTO errorKeyType(String keyTypeError) {
        return PixRequestDTO.builder ()
                .keyType (keyTypeError)
                .keyValue (KEY_VALUE_CPF)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENCY_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static PixRequestDTO errorAccountType(String accountType) {
        return PixRequestDTO.builder ()
                .keyType (KEY_TYPE_CELULAR)
                .keyValue (KEY_VALUE_CELULAR)
                .accountType (accountType)
                .agencyNumber (AGENCY_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static PixRequestDTO errorKeyValueCelular(String keyValue) {
        return PixRequestDTO.builder ()
                .keyType (KEY_TYPE_CELULAR)
                .keyValue (keyValue)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENCY_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static PixRequestDTO errorKeyValueEmail(String keyValue) {
        return PixRequestDTO.builder ()
                .keyType (KEY_TYPE_EMAIL)
                .keyValue (keyValue)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENCY_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }
}
