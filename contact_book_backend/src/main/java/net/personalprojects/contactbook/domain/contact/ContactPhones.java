package net.personalprojects.contactbook.domain.contact;

import jakarta.validation.constraints.Size;
import net.personalprojects.contactbook.model.ContactPhone;

import java.util.Set;

public class ContactPhones {
    @Size(min = 1, max = 3, message = "Invalid contact phones. Only between 1 and 3 phones")
    private Set<ContactPhone> _value;
    public ContactPhones(final Set<ContactPhone> value) {
        this._value = value;
    }
    public Set<ContactPhone> value() {
        return this._value;
    }
}
