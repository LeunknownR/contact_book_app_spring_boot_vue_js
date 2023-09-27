package net.personalprojects.contactbook.repository;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.model.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> getAllContacts(final String contactName, final String contactPhoneNumber);
    Contact findContactById(final long contactId);
    ResponseActionMessages addContact(final Contact contact);
    ResponseActionMessages editContact(final Contact contact);
    void removeContact(final long contactId);
    List<Contact> getFavoriteContacts();
    void toggleFavoriteContact(final Long contactId);
}
