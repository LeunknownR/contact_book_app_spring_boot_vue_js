package net.personalprojects.contactbook.domain.contact;

import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.model.ContactPhone;

import java.util.Set;

public class EditContactForm extends ContactForm {
    private ContactId _id;
    private ContactPhones _phones;
    public EditContactForm(ContactDTO contactDTO) {
        super(contactDTO);
        this._id = new ContactId(contactDTO.getId());
        this._phones = new ContactPhones(contactDTO.getPhonesToEdit());
    }
    public long id() {
        return this._id.value();
    }
    public Set<ContactPhone> phones() {
        return this._phones.value();
    }
}
