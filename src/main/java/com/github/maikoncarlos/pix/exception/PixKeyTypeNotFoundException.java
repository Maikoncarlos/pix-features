package com.github.maikoncarlos.pix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static java.lang.String.format;

public class PixKeyTypeNotFoundException extends PixException {

    private final String keyTypeAndClientName;

    public PixKeyTypeNotFoundException(String keyTypeAndClientName) {
        this.keyTypeAndClientName = keyTypeAndClientName;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle ("Pix already resgistration ");
        pb.setDetail (format ("Não retornou nenhum dado com keyType and clientName: %s ! ", keyTypeAndClientName));

        return pb;
    }
}