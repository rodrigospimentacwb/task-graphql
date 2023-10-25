package br.com.pimenta.tarefasGraphql.domains.commons.exception.exceptions;

public class ExecutionFailed extends RuntimeException {
    public ExecutionFailed(String message) {
        super(message);
    }
}
