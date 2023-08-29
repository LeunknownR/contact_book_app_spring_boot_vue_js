package net.personalprojects.contactbook.domain.contactphone;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactPhoneId {
    private static final String ERROR_MESSAGE = "Invalid contact id";
    private static final int MIN_VALUE = 1;
    final Long _value;
    public ContactPhoneId(final Long value) {
        if (value != null && value < MIN_VALUE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public Long value() {
        return this._value;
    }
}