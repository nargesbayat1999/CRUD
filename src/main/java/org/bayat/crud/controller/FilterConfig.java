package org.bayat.crud.controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.bayat.crud.model.GenericResponse;
import org.bayat.crud.model.enums.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FilterConfig extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
        chain.doFilter(wrapper, servletResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericResponse<String>> handleDefaultException(Exception ex, ContentCachingRequestWrapper request) {
        String reqBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
        GenericResponse<String> response = new GenericResponse<>(ex.getMessage(), Message.DATA_NOT_FOUND.getMessageStatus(), reqBody);
        logger.error("response : " + response);
        return ResponseEntity.notFound().build();
    }

}
