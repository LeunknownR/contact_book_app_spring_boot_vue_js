package net.personalprojects.contactbook.service.implementation;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneVO;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.model.ContactPhone;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository repository;
    @Override
    public List<Contact> getAllContacts(final ContactFilters filters) {
        return repository.getAllContacts(filters.contactName(), filters.contactPhoneNumber());
    }
    @Override
    public Contact findContactById(long contactId) {
        return repository.findContactById(contactId);
    }
    @Override
    public ResponseActionMessages addContact(final AddContactForm addContactForm) {
        final Contact contact = Contact
                .builder()
                .name(addContactForm.name())
                .email(addContactForm.email())
                .category(
                    ContactCategory
                        .builder()
                        .id(addContactForm.categoryId())
                        .build()
                )
                .isFavorite(addContactForm.isFavorite())
                .phones(new HashSet<>())
                .build();
        for (final String number : addContactForm.phones()) {
            final ContactPhone phone = new ContactPhone();
            phone.setNumber(number);
            contact.addContactPhone(phone);
        }
        return repository.addContact(contact);
    }
    @Override
    public ResponseActionMessages editContact(final EditContactForm editContactForm) {
        final Contact contact = Contact.builder()
                .id(editContactForm.id())
                .name(editContactForm.name())
                .email(editContactForm.email())
                .category(
                    ContactCategory
                        .builder()
                        .id(editContactForm.categoryId())
                        .build()
                )
                .isFavorite(editContactForm.isFavorite())
                .phones(new HashSet<>())
                .build();
        for (final ContactPhoneVO contactPhoneVO : editContactForm.phones()) {
            final ContactPhone phone = new ContactPhone();
            phone.setId(contactPhoneVO.id());
            phone.setNumber(contactPhoneVO.number());
            contact.addContactPhone(phone);
        }
        return repository.editContact(contact);
    }
    @Override
    public void removeContact(final ContactId contactId) {
        repository.removeContact(contactId.value());
    }
    @Override
    public List<Contact> getFavoriteContacts() {
        return repository.getFavoriteContacts();
    }
    @Override
    public void toggleFavoriteContact(final ContactId contactId) {
        repository.toggleFavoriteContact(contactId.value());
    }
}
