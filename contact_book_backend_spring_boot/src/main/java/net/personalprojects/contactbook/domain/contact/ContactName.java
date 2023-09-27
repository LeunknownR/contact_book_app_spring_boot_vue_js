package net.personalprojects.contactbook.domain.contact;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.exception.InvalidContactException;

@EqualsAndHashCode
public class ContactName {
    private static final String ERROR_MESSAGE = "Invalid contact name";
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 50;
    private final String _value;
    public ContactName(final String value) {
        if (value == null || value.length() < MIN_SIZE || value.length() > MAX_SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
