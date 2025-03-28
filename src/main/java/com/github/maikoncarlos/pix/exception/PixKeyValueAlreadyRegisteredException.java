package com.github.maikoncarlos.pix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static java.lang.String.format;


public class PixKeyValueAlreadyRegisteredException extends PixException {

    private final String keyValue;

    public PixKeyValueAlreadyRegisteredException(String keyValue) {
        this.keyValue = keyValue;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle ("Pix already resgistration ");
        pb.setDetail (format ("Chave Pix %s já cadastrada! ", keyValue));

        return pb;
    }
}
