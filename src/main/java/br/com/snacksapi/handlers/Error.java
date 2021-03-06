package br.com.snacksapi.handlers;

import lombok.Data;

@Data
public class Error {

    private final String fieldName;
    private final String message;

    public Error(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
