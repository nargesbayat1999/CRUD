package org.bayat.crud.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GlobalControllerExceptionHandlerTest {

    private GlobalControllerExceptionHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new GlobalControllerExceptionHandler();
    }

    @Test
    public void handleConversionTest() {
        RuntimeException runtimeException = new ConversionFailedException(null, null, "canvers fail", new ArithmeticException("INVALID"));

        ResponseEntity<String> response = handler.handleConversion(runtimeException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
    @Test
    public void handleBookNotFoundTest() {
        RuntimeException runtimeException = new RuntimeException();
        ResponseEntity<String> response = handler.handleBookNotFound(runtimeException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



}
