package net.personalprojects.contactbook.domain.contact;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactEmail {
    private static final String ERROR_MESSAGE = "Invalid contact email";
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 30;
    private final String _value;
    public ContactEmail(final String value) {
        if (value == null || value.length() < MIN_SIZE || value.length() > MAX_SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
