package net.personalprojects.contactbook.domain.contact;

import jakarta.validation.constraints.Size;

import java.util.Arrays;

public class ContactPhoneNumbers {
    @Size(min = 1, max = 3, message = "Invalid contact phone numbers. Only between 1 and 3 phones")
    private String[] _value;
    public ContactPhoneNumbers(final String[] value) {
        this._value = Arrays.stream(value)
                .map(ContactPhoneNumber::new)
                .toArray(String[]::new);
    }
    public String[] value() {
        return this._value;
    }
}
