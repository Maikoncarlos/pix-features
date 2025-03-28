package com.github.maikoncarlos.pix.fatory;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;

import static com.github.maikoncarlos.pix.util.MocksUtil.*;

public class PixRequestDTOFactory {

    public static PixRequestDTO success() {
        return PixRequestDTO.builder ()
                .keyType (KEY_TYPE)
                .keyValue (KEY_VALUE)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static PixRequestDTO success(String keyTypeError) {
        return PixRequestDTO.builder ()
                .keyType (keyTypeError)
                .keyValue (KEY_VALUE)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }
}
