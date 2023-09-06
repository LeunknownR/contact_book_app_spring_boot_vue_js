package net.personalprojects.contactbook.domain.contact;

import net.personalprojects.contactbook.domain.contactphone.ContactPhoneList;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneVO;
import net.personalprojects.contactbook.dto.ContactDTO;

import java.util.Set;

public class EditContactForm extends ContactForm {
    private ContactId _id;
    private ContactPhoneList _phones;
    public EditContactForm(ContactDTO contactDTO) {
        super(contactDTO);
        this._id = new ContactId(contactDTO.getId());
        this._phones = new ContactPhoneList(contactDTO.getPhonesToEdit());
    }
    public long id() {
        return this._id.value();
    }
    public Set<ContactPhoneVO> phones() {
        return this._phones.value();
    }
}
