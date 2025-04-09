package org.bayat.crud.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.bayat.crud.model.enums.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;


@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<GenericResponse<DataDTO>> handleConversion(RuntimeException ex, HttpServletRequest request) {
        //  return ResponseEntity.badRequest().body(new GenericResponse<>(ex.getMessage(),Message.USER_ERROR.getMessageStatus(), null));
        DataDTO requestData = new DataDTO();
        requestData.setName(request.getRequestURI());
        GenericResponse<DataDTO> response = new GenericResponse<>(ex.getMessage(), Message.USER_ERROR.getMessageStatus(), requestData);
        logger.error("Handling ConversionFailedException - Response: {}", response);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericResponse<DataDTO>> handleBookNotFound(RuntimeException ex, HttpServletRequest request) {
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse<>(ex.getMessage(),Message.DATA_NOT_FOUND.getMessageStatus(), null));

        DataDTO requestData = new DataDTO();
        requestData.setName(request.getRequestURI());

        GenericResponse<DataDTO> response = new GenericResponse<>(ex.getMessage(), Message.DATA_NOT_FOUND.getMessageStatus(), requestData);
        logger.error("Handling ConversionFailedException -Response: {}", response);
        return ResponseEntity.notFound().build();

    }




}

