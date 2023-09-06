package net.personalprojects.contactbook.domain.contactphone;

import net.personalprojects.contactbook.dto.ContactPhoneDTO;

public class ContactPhoneVO {
    private final ContactPhoneId _id;
    private final ContactPhoneNumber _number;
    public ContactPhoneVO(ContactPhoneDTO value) {
        this._id = new ContactPhoneId(value.getId());
        this._number = new ContactPhoneNumber(value.getNumber());
    }
    public Long id() {
        return this._id.value();
    }
    public String number() {
        return this._number.value();
    }
}
