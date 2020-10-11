package br.com.snacksapi.exceptions;

public class NotFoundException extends RuntimeException {
    private final String fieldName;
    private final Long id;

    public NotFoundException(String fieldName, Long id) {
        this.fieldName = fieldName;
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Long getId() {
        return id;
    }
}
