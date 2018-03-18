package com.felipiberdun.order.application.handler;

import com.felipiberdun.order.exception.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@RestControllerAdvice
public class ApiExecptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleBaseException(final BaseException ex, final WebRequest webRequest) {
        final HttpStatus responseStatus = Optional.ofNullable(ex.getClass().getAnnotation(ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(responseStatus)
                .headers(new HttpHeaders())
                .body(ex.getMessage()); //Create a better way to outut the exception content
    }

}
