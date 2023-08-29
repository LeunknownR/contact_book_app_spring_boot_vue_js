package net.personalprojects.contactbook.domain.contactphone;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactPhoneNumber {
    private static final String ERROR_MESSAGE = "Invalid contact phone number";
    private static final int SIZE = 9;
    private String _value;
    public ContactPhoneNumber(String value) {
        if (value == null || value.length() != SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
