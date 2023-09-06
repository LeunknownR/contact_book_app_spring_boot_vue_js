package net.personalprojects.contactbook.domain.contactfilters;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactNameFilter {
    private static final String ERROR_MESSAGE = "Invalid contact name filter";
    private static final int MAX_SIZE = 50;
    private final String _value;
    public ContactNameFilter(final String value) {
        if (value == null || value.length() > MAX_SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
