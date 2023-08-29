package net.personalprojects.contactbook.domain.contactcategory;

import net.personalprojects.contactbook.exception.InvalidContactException;

public class ContactCategoryId {
    private static final String ERROR_MESSAGE = "Invalid contact category id";
    private static final int SIZE = 3;
    final String _value;
    public ContactCategoryId(final String value) {
        if (value == null || value.length() != SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
