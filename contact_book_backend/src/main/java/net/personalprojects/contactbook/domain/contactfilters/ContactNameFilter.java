package net.personalprojects.contactbook.domain.contactfilters;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactNameFilter {
    @NotNull
    @Size(max = 50, message = "Invalid contact name filter")
    private String _value;
    public ContactNameFilter(final String name) {
        this._value = name;
    }
    public String value() {
        return this._value;
    }
}
