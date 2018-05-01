package com.meteoradesigner.util.exception;

import com.meteoradesigner.util.ServiceValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//TODO documentation
//TODO check if annotations parameter is needed.
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ApplicationException.class)
    private ResponseEntity<ErrorInfo> handleApplicationException(
            HttpServletRequest request,
            ApplicationException exception) {
        return new ResponseEntity<>(
                logAndGetErrorInfo(
                        exception,
                        request.getRequestURI(),
                        true,
                        MessageUtil.getMessage(exception)),
                exception.getHttpStatus());
    }

    //second method without ResponseEntity
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    private ErrorInfo handleException(
            HttpServletRequest request,
            Exception exception) {
        return logAndGetErrorInfo(
                exception,
                request.getRequestURI(),
                false,
                exception.getLocalizedMessage());
    }

    private ErrorInfo logAndGetErrorInfo(Exception exception,
                                         String uri,
                                         boolean isApplicationException,
                                         String description) {
        Throwable primeCause = ServiceValidatorUtil.getPrimeCause(exception);

        if (isApplicationException) {
            LOGGER.warn("primeCause: {}; url: {}", primeCause, uri);
        } else {
            LOGGER.error("primeCause: {}; url: {}", primeCause, uri);
        }

        return new ErrorInfo(
                uri,
                description,
                primeCause.toString()
        );
    }

}