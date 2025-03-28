package com.github.maikoncarlos.pix.fatory;

import com.github.maikoncarlos.pix.controller.dto.PixResponseDTO;

import static com.github.maikoncarlos.pix.util.MocksUtil.*;

public class PixResponseDTOFactory {

    public static PixResponseDTO success() {
        return PixResponseDTO.builder ()
                .id (ID)
                .keyType (KEY_TYPE)
                .keyValue (KEY_VALUE)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME)
                .dateOfInclusion (DATA_OF_INCLUSION).build ();
    }
}
