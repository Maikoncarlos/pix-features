package com.github.maikoncarlos.pix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PixExistsByIdException extends PixException {
    private final String id;

    public PixExistsByIdException(String id) {
        this.id = id;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle ("Pix already disabled");
        pb.setDetail ("Chave Pix com o id " + id + " já foi desativada.");

        return pb;
    }
}
