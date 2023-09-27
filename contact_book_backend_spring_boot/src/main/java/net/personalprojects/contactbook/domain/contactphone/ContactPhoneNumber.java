package net.personalprojects.contactbook.domain.contactphone;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.utils.Validators;

@EqualsAndHashCode
public class ContactPhoneNumber {
    private static final String ERROR_MESSAGE = "Invalid contact phone number";
    private static final int SIZE = 9;
    private String _value;
    public ContactPhoneNumber(final String value) {
        if (value == null
            || value.length() != SIZE
            || !value.matches(Validators.NUMBER_REGEX))
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
