package net.personalprojects.contactbook.repository;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.model.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> getAllContacts(final String contactName, final String contactPhoneNumber);
    ResponseActionMessages addContact(Contact contact);
    ResponseActionMessages editContact(Contact contact);
    void removeContact(long contactId);
    List<Contact> getFavoriteContacts();
    void toggleFavoriteContact(Long contactId);
}
