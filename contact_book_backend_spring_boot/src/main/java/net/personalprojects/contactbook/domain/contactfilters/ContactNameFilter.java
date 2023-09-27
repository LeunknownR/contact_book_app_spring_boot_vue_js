package net.personalprojects.contactbook.domain.contactfilters;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.exception.InvalidContactFiltersExpection;

@EqualsAndHashCode
public class ContactNameFilter {
    private static final String ERROR_MESSAGE = "Invalid contact name filter";
    private static final int MAX_SIZE = 50;
    private final String _value;
    public ContactNameFilter(final String value) {
        if (value == null || value.length() > MAX_SIZE)
            throw new InvalidContactFiltersExpection(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
