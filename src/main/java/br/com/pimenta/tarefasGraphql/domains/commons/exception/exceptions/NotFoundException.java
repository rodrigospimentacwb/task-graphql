package br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
