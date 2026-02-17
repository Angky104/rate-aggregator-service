package com.allobank.angky.rateaggregatorservice.exception;

import com.allobank.angky.rateaggregatorservice.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidResourceTypeException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidResourceType(
            InvalidResourceTypeException ex,
            HttpServletRequest request
    ) {

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .timestamp(OffsetDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .timestamp(OffsetDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponseDTO> handleWebClientResponse(
            WebClientResponseException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .timestamp(OffsetDateTime.now().toString())
                .status(ex.getStatusCode().value())
                .error(ex.getStatusText())
                .message(ex.getResponseBodyAsString())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleWebClientRequest(
            WebClientRequestException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .timestamp(OffsetDateTime.now().toString())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                .message("External service unavailable: " + ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .timestamp(OffsetDateTime.now().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpStatusCodeException(
            HttpStatusCodeException ex,
            HttpServletRequest request
    ) {

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .timestamp(OffsetDateTime.now().toString())
                .status(ex.getStatusCode().value())
                .error(ex.getStatusText())
                .message(ex.getResponseBodyAsString())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }
}

