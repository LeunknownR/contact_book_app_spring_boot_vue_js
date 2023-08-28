package net.personalprojects.contactbook.domain.contact;

import net.personalprojects.contactbook.dto.ContactDTO;

import java.util.Set;

public class AddContactForm extends ContactForm {
    private ContactPhoneNumbers _phones;
    public AddContactForm(ContactDTO contactDTO) {
        super(contactDTO);
        this._phones = new ContactPhoneNumbers(contactDTO.getPhonesToAdd());
    }
    public String[] phones() {
        return this._phones.value();
    }
}
