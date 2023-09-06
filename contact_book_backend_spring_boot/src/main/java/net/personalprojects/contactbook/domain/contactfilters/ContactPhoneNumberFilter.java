package net.personalprojects.contactbook.domain.contactfilters;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactPhoneNumberFilter {
    private static final String ERROR_MESSAGE = "Invalid contact phone number";
    private static final int MAX_SIZE = 9;
    private String _value;
    public ContactPhoneNumberFilter(final String value) {
        if (value == null || value.length() > MAX_SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
