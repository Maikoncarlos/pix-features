package com.github.maikoncarlos.pix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PixException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus (HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle ("Pix Internal Server Error");

        return pb;
    }
}
