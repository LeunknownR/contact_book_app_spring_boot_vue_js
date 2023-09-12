package net.personalprojects.contactbook.service;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts(final ContactFilters filters);
    Contact findContactById(final long contactId);
    ResponseActionMessages addContact(final AddContactForm addContactForm);
    ResponseActionMessages editContact(final EditContactForm editContactForm);
    void removeContact(final ContactId contactId);
    List<Contact> getFavoriteContacts();
    void toggleFavoriteContact(final ContactId contactId);
}
