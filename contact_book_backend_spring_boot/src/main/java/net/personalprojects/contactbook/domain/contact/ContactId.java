package net.personalprojects.contactbook.domain.contact;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactId {
    private static final String ERROR_MESSAGE = "Invalid contact id";
    private static final int MIN_VALUE = 1;
    final long _value;
    public ContactId(final long value) {
        if (value < MIN_VALUE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public long value() {
        return this._value;
    }
}
