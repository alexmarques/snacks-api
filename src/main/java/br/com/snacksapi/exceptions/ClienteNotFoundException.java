package br.com.snacksapi.exceptions;

public class ClienteNotFoundException extends NotFoundException {
    public ClienteNotFoundException(String fieldName, Long clienteId) {
        super(fieldName, clienteId);
    }
}
