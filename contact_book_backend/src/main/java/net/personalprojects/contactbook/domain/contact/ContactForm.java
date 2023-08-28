package net.personalprojects.contactbook.domain.contact;

import lombok.Getter;
import net.personalprojects.contactbook.domain.contactcategory.ContactCategoryId;
import net.personalprojects.contactbook.dto.ContactDTO;

public abstract class ContactForm {
    private ContactName _name;
    private ContactEmail _email;
    @Getter
    private boolean isFavorite;
    private ContactCategoryId _categoryId;
    public ContactForm(final ContactDTO contactDTO) {
        this._name = new ContactName(contactDTO.getName());
        this._email = new ContactEmail(contactDTO.getEmail());
        this.isFavorite = contactDTO.getIsFavorite();
        this._categoryId = new ContactCategoryId(contactDTO.getCategoryId());
    }
    public String name() {
        return _name.value();
    }
    public String email() {
        return _email.value();
    }
    public String categoryId() {
        return _categoryId.value();
    }
}
