package com.felipiberdun.order.application.handler;

import com.felipiberdun.order.exception.BaseException;
import com.felipiberdun.order.exception.InvalidTokenException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@RestControllerAdvice
public class ApiExecptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleBaseException(final BaseException ex, final WebRequest webRequest) {
        final HttpStatus responseStatus = Optional.ofNullable(ex.getClass().getAnnotation(ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(responseStatus)
                .headers(new HttpHeaders())
                .body(ex.getMessage()); //Create a better way to outut the exception content
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingServletParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body("Parameter missing " + ex.getParameterName());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void httpMessageNotReadableException(HttpMessageNotReadableException ex) {
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AuthenticationException.class, InvalidTokenException.class,
            UsernameNotFoundException.class, BadCredentialsException.class})
    public void authenticationException(AuthenticationException e) {
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoHandlerFoundException.class, MethodArgumentTypeMismatchException.class,
            EntityNotFoundException.class})
    public void resourceNotFoundException() {
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void methodNotAllowedException() {
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void dataIntegrityViolationException() {
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void mediaTypeNotSupportedException() {
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Set<String> methodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " - " + error.getDefaultMessage())
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Set<String> constraintViolationException(final ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public void violationException(ValidationException e) {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public void handleThrowable(Throwable ex) {
        LoggerFactory.getLogger(this.getClass()).error("Internal Server Error!", ex);
    }

}
