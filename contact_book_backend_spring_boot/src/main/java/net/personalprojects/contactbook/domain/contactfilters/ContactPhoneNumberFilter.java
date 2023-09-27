package net.personalprojects.contactbook.domain.contactfilters;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.exception.InvalidContactFiltersExpection;
import net.personalprojects.contactbook.utils.Validators;

@EqualsAndHashCode
public class ContactPhoneNumberFilter {
    private static final String ERROR_MESSAGE = "Invalid contact phone number";
    private static final int MAX_SIZE = 9;
    private final String _value;
    public ContactPhoneNumberFilter(final String value) {
        if (
                value == null
                || value.length() > MAX_SIZE
                || (value.length() > 0 && !value.matches(Validators.NUMBER_REGEX))
        )
            throw new InvalidContactFiltersExpection(ERROR_MESSAGE);
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
