package net.personalprojects.contactbook.domain.contact;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ContactName {
    @NotNull
    @Size(min = 1, max = 50, message = "Invalid contact name")
    private String _value;
    public ContactName(final String name) {
        this._value = name;
    }
    public String value() {
        return this._value;
    }
}
