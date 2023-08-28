package net.personalprojects.contactbook.domain.contact;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ContactEmail {
    @NotNull
    @Size(min = 1, max = 30, message = "Invalid contact email")
    private final String _value;
    public ContactEmail(final String email) {
        this._value = email;
    }
    public String value() {
        return this._value;
    }
}
