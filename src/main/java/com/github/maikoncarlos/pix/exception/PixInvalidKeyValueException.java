package com.github.maikoncarlos.pix.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Slf4j
public class PixInvalidKeyValueException extends PixException {
    private final String message;

    public PixInvalidKeyValueException(String message) {
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle ("Pix keyValue invalid");
        pb.setDetail (message);

        log.error (" Error {} ", pb);
        return pb;
    }
}
