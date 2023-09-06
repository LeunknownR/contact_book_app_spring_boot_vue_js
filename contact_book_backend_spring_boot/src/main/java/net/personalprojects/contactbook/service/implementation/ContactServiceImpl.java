package net.personalprojects.contactbook.service.implementation;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneVO;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactPhone;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository repository;
    @Override
    public List<Contact> getAllContacts(final ContactFilters filters) {
        return repository.getAllContacts(filters.contactName(), filters.contactPhoneNumber());
    }
    @Override
    public ResponseActionMessages addContact(AddContactForm addContactForm) {
        final Contact contact = new Contact();
        contact.setName(addContactForm.name());
        contact.setEmail(addContactForm.email());
        contact.setIsFavorite(addContactForm.isFavorite());
        contact.setCategoryId(addContactForm.categoryId());
        for (String number : addContactForm.phones()) {
            final ContactPhone phone = new ContactPhone();
            phone.setNumber(number);
            contact.addContactPhone(phone);
        }
        return repository.addContact(contact);
    }
    @Override
    public ResponseActionMessages editContact(EditContactForm editContactForm) {
        final Contact contact = new Contact();
        contact.setId(editContactForm.id());
        contact.setName(editContactForm.name());
        contact.setEmail(editContactForm.email());
        contact.setIsFavorite(editContactForm.isFavorite());
        contact.setCategoryId(editContactForm.categoryId());
        for (ContactPhoneVO contactPhoneVO : editContactForm.phones()) {
            final ContactPhone phone = new ContactPhone();
            phone.setId(contactPhoneVO.id());
            phone.setNumber(contactPhoneVO.number());
            contact.addContactPhone(phone);
        }
        return repository.editContact(contact);
    }
    @Override
    public void removeContact(ContactId contactId) {
        repository.removeContact(contactId.value());
    }
    @Override
    public List<Contact> getFavoriteContacts() {
        return repository.getFavoriteContacts();
    }
    @Override
    public void toggleFavoriteContact(final ContactId contactId) {
        System.out.println(contactId.value());
        repository.toggleFavoriteContact(contactId.value());
    }
}
