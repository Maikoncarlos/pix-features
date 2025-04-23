package com.github.maikoncarlos.pix.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class MocksUtil {

    public static final UUID ID = UUID.fromString ("19d60968-d469-4df6-90e9-35a8aaf00905");
    public static final UUID ID_ERROR = UUID.fromString ("19d60968-d469-4df6-90e9-35a8aaf00904");
    public static final String KEY_TYPE_CPF = "cpf";
    public static final String KEY_TYPE_CELULAR = "celular";
    public static final String KEY_TYPE_EMAIL = "email";
    public static final String KEY_TYPE_ERROR = "xxx";
    public static final String KEY_VALUE_CPF = "98765432100";
    public static final String KEY_VALUE_CELULAR = "+5511987654321";
    public static final String KEY_VALUE_EMAIL = "testeMock@email.com";
    public static final String ACCOUNT_TYPE = "corrente";
    public static final int AGENCY_NUMBER = 2566;
    public static final int ACCOUNT_NUMBER = 123465;
    public static final String CLIENT_NAME = "João";
    public static final String CLIENT_LAST_NAME = "Silva";
    public static final LocalDateTime DATA_OF_INCLUSION = LocalDateTime.now ();
    public static final LocalDateTime DATA_OF_INACTIVATION = LocalDateTime.now ();
}
