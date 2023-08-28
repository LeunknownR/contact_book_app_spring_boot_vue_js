package net.personalprojects.contactbook.domain.contact;

import jakarta.validation.constraints.Size;

public class ContactPhoneNumber {
    @Size(min = 9, max = 9, message = "Invalid contact phone number")
    private String _value;
    public ContactPhoneNumber(String value) {
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
