package org.bayat.crud.controller;

import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.dto.DataDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GlobalControllerExceptionHandlerTest {

    private GlobalControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalControllerExceptionHandler();
    }

    @Test
     void handleConversionTest() {
        RuntimeException runtimeException = new ConversionFailedException(null, null, "fail", new ArithmeticException("INVALID"));
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setRequestURI("/test-uri");


        ResponseEntity<GenericResponse<DataDTO>> response = handler.handleConversion(runtimeException,mockRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
    @Test
     void handleBookNotFoundTest() {

        RuntimeException runtimeException = new RuntimeException();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setRequestURI("/test-uri");
        ResponseEntity<GenericResponse<DataDTO>> response = handler.handleBookNotFound(runtimeException,mockRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



}
