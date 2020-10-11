package br.com.snacksapi.exceptions;

public class SnackNotFoundException extends NotFoundException {
    public SnackNotFoundException(String fieldName, Long snackId) {
        super(fieldName, snackId);
    }
}
