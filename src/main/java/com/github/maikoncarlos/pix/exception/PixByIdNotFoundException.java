package com.github.maikoncarlos.pix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PixByIdNotFoundException extends PixException {

    private final String id;
    public PixByIdNotFoundException(String id) {
        this.id = id;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Pix not found");
        pb.setDetail("Chave Pix não encontrada com o id " + id + ".");

        return pb;
    }
}
