package net.personalprojects.contactbook.domain.contact;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneNumber;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneNumberList;
import net.personalprojects.contactbook.dto.ContactDTO;

import java.util.Arrays;

@EqualsAndHashCode
public class AddContactForm extends ContactForm {
    private ContactPhoneNumberList _phones;
    public AddContactForm(ContactDTO contactDTO) {
        super(contactDTO);
        this._phones = new ContactPhoneNumberList(contactDTO.getPhonesToAdd());
    }
    public String[] phones() {
        return Arrays.stream(this._phones.value()).map(ContactPhoneNumber::value).toArray(String[]::new);
    }
}
