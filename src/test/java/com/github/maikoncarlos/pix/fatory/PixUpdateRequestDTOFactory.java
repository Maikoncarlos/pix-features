package com.github.maikoncarlos.pix.fatory;

import com.github.maikoncarlos.pix.controller.dto.PixUpdateRequestDTO;

import java.util.UUID;

import static com.github.maikoncarlos.pix.util.MocksUtil.*;

public class PixUpdateRequestDTOFactory {

    public static PixUpdateRequestDTO success() {
        return PixUpdateRequestDTO.builder ()
                .id (ID.toString ())
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static PixUpdateRequestDTO error(UUID idError) {
        return PixUpdateRequestDTO.builder ()
                .id (idError.toString ())
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }
}
