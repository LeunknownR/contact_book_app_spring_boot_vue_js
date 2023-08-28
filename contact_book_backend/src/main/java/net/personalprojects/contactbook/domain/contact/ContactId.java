package net.personalprojects.contactbook.domain.contact;

import jakarta.validation.constraints.Min;

public class ContactId {
    @Min(value = 1, message = "Invalid contact id")
    final long _value;
    public ContactId(long value) {
        this._value = value;
    }
    public long value() {
        return this._value;
    }
}
