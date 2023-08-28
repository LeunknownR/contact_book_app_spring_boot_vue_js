package net.personalprojects.contactbook.service;

import jakarta.validation.Valid;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAllContacts(final ContactFilters filters);
    void addContact(final AddContactForm addContactForm);
    void editContact(final EditContactForm editContactForm);
    void removeContact(final ContactId contactId);
    List<ContactDTO> getFavoriteContacts();
    void toggleFavoriteContact(final ContactId contactId);
}
