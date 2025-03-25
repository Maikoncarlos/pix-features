package com.github.maikoncarlos.pix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static java.lang.String.format;

public class PixByAgencyAndAccountNotFoundException extends PixException {
    private final int agency;
    private final int account;

    public PixByAgencyAndAccountNotFoundException(int agency, int account) {
        this.agency = agency;
        this.account = account;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus (HttpStatus.NOT_FOUND);

        pb.setTitle ("Pix not found");
        pb.setDetail (format ("Chave Pix não encontrada com agência: %d e conta: %d", agency, account));

        return pb;
    }
}
