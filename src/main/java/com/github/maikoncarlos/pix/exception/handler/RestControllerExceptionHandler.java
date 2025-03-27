package com.github.maikoncarlos.pix.exception.handler;

import com.github.maikoncarlos.pix.exception.PixException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private static final String TITLE = "Invalid Params na Request!";

    @ExceptionHandler(PixException.class)
    public ProblemDetail handlerPixException(PixException ex) {
        return ex.toProblemDetail ();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErrors = e.getFieldErrors ()
                .stream ()
                .map (f -> new InvalidParam (f.getField (), f.getDefaultMessage ()))
                .toList ();

        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle (TITLE);
        pb.setProperty ("invalid-params", fieldErrors);

        return pb;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail missingServletRequestParameterException(MissingServletRequestParameterException e){

        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle (TITLE);
        pb.setDetail (e.getBody ().getDetail ());

        return pb;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail illegalArgumentException(IllegalArgumentException e){
        var pb = ProblemDetail.forStatus (HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle (TITLE);
        pb.setDetail (e.getMessage ());

        return pb;
    }

    private record InvalidParam(String name, String reason) {
    }
}