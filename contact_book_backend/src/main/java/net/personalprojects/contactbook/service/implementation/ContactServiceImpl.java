package net.personalprojects.contactbook.service.implementation;

import jakarta.validation.Valid;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.dto.ContactDTO;
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
    private List<ContactDTO> contactsToContactDTO(List<Contact> contacts) {
        return contacts.stream().map(record -> {
            final ContactDTO contact = new ContactDTO();
            contact.setId(record.getId());
            contact.setName(record.getName());
            contact.setEmail(record.getEmail());
            contact.setIsFavorite(record.getIsFavorite());
            contact.setCategoryId(record.getCategoryId());
            contact.setPhonesToEdit(record.getPhones());
            return contact;
        }).toList();
    }
    @Override
    public List<ContactDTO> getAllContacts(final ContactFilters filters) {
        return contactsToContactDTO(repository.getAllContacts(filters.contactName(), filters.contactPhoneNumber()));
    }
    @Override
    public void addContact(AddContactForm addContactForm) {
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
        repository.addContact(contact);
    }
    @Override
    public void editContact(EditContactForm editContactForm) {
        final Contact contact = new Contact();
        contact.setId(editContactForm.id());
        contact.setName(editContactForm.name());
        contact.setEmail(editContactForm.email());
        contact.setIsFavorite(editContactForm.isFavorite());
        contact.setCategoryId(editContactForm.categoryId());
        contact.setPhones(editContactForm.phones());
        repository.editContact(contact);
    }
    @Override
    public void removeContact(ContactId contactId) {
        repository.removeContact(contactId.value());
    }
    @Override
    public List<ContactDTO> getFavoriteContacts() {
        return contactsToContactDTO(repository.getFavoriteContacts());
    }
    @Override
    public void toggleFavoriteContact(final ContactId contactId) {
        repository.toggleFavoriteContact(contactId.value());
    }
}
