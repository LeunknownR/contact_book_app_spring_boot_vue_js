package net.personalprojects.contactbook.domain.contactphone;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.exception.InvalidContactException;

import java.util.Set;

@EqualsAndHashCode
public class ContactPhoneNumberList {
    private static final String ERROR_MESSAGE = "Invalid contact phone numbers. Only between 1 and 3 phones";
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 3;
    private ContactPhoneNumber[] _value;
    public ContactPhoneNumberList(final Set<String> value) {
        if (value == null
            || value.size() < MIN_SIZE
            || value.size() > MAX_SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value.stream().map(ContactPhoneNumber::new).toArray(ContactPhoneNumber[]::new);
    }
    public ContactPhoneNumber[] value() {
        return this._value;
    }
}
