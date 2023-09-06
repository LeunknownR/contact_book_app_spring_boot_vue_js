package net.personalprojects.contactbook.exception;

public class InvalidContactException extends RuntimeException {
    public InvalidContactException(final String message) {
        super(message);
    }
}