package net.personalprojects.contactbook.domain.contactfilters;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public class ContactPhoneNumberFilter {
    @Size(max = 9, message = "Invalid contact phone number")
    private String _value;
    public ContactPhoneNumberFilter(final String value) {
        this._value = value;
        valid(this);
    }
    private void valid(@Valid ContactPhoneNumberFilter a) {}
    public String value() {
        return this._value;
    }
}
