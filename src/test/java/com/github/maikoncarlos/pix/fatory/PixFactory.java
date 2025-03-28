package com.github.maikoncarlos.pix.fatory;

import com.github.maikoncarlos.pix.repository.entity.Pix;

import java.time.LocalDateTime;

import static com.github.maikoncarlos.pix.util.MocksUtil.*;

public class PixFactory {

    public static Pix requestSuccess() {
        return Pix.builder ()
                .keyType (KEY_TYPE_CPF)
                .keyValue (KEY_VALUE_CPF)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME).build ();
    }

    public static Pix responseSuccess() {
        return Pix.builder ()
                .id (ID)
                .keyType (KEY_TYPE_CPF)
                .keyValue (KEY_VALUE_CPF)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME)
                .dateOfInclusion (DATA_OF_INCLUSION).build ();
    }

    public static Pix responseDisableSuccess(LocalDateTime now) {
        return Pix.builder ()
                .id (ID)
                .keyType (KEY_TYPE_CPF)
                .keyValue (KEY_VALUE_CPF)
                .accountType (ACCOUNT_TYPE)
                .agencyNumber (AGENT_NUMBER)
                .accountNumber (ACCOUNT_NUMBER)
                .clientName (CLIENT_NAME)
                .clientLastName (CLIENT_LAST_NAME)
                .dateOfInclusion (DATA_OF_INCLUSION)
                .dateOfInactivation (DATA_OF_INACTIVATION)
                .active (false).build ();
    }
}
