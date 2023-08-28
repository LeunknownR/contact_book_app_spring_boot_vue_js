package net.personalprojects.contactbook.domain.contactcategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactCategoryId {
    @Size(min = 3, max = 3, message = "Invalid contact category id")
    final String _value;
    public ContactCategoryId(final String value) {
        this._value = value;
    }
    public String value() {
        return this._value;
    }
}
