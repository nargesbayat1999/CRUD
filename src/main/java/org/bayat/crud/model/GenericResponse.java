package org.bayat.crud.model;

import lombok.Data;

@Data
public class GenericResponse<T> {

    private String errorCode;
    private String message;
    private T data;

    public GenericResponse(String message, String errorCode, T data) {
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }
}

