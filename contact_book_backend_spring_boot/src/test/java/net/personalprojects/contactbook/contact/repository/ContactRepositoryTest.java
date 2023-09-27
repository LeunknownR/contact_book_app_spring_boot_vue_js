package net.personalprojects.contactbook.contact.repository;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.repository.ContactRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository repository;
    @Test
    @DisplayName("it should be returned contacts")
    void shouldBeReturnedContacts() {
        final List<Contact> contacts = repository.getAllContacts("", "");
        MatcherAssert.assertThat(contacts, Matchers.notNullValue());
    }
    private Optional<Contact> makeAddContact(final Contact newContact) {
        final ResponseActionMessages message = repository.addContact(newContact);
        if (message != ResponseActionMessages.SUCCESS)
            return Optional.empty();
        final List<Contact> contacts = repository.getAllContacts(newContact.getName(), "");
        return contacts.stream()
                .filter(contact -> contact.getName().equals(newContact.getName()))
                .findFirst();
    }
    private Optional<Contact> makeAddContact() {
        return makeAddContact(ContactMockData.createContactToAdd());
    }
    @Test
    @DisplayName("it should be returned contact when add contact")
    @Transactional
    void shouldBeReturnedContactWhenAddContact() {
        final Optional<Contact> contactShouldExist = makeAddContact();
        MatcherAssert.assertThat(contactShouldExist.isPresent(), Matchers.is(true));
    }
    @Test
    @DisplayName("it should be changed contact name if contact not exists when edit contact")
    @Transactional
    void shouldBeChangedContactNameIfContactNotExistsWhenEditContact() {
        final Optional<Contact> contactShouldExist = makeAddContact();
        if (contactShouldExist.isEmpty()) {
            Assertions.fail();
            return;
        }
        Contact contact = contactShouldExist.get();
        final String newContactName = "Carlos Eduardo Sanchez";
        contact.setName(newContactName);
        final ResponseActionMessages message = repository.editContact(contact);
        if (message != ResponseActionMessages.SUCCESS) {
            Assertions.fail();
            return;
        }
        contact = repository.findContactById(contact.getId());
        MatcherAssert.assertThat(contact.getName(), Matchers.equalTo(newContactName));
    }
    @Test
    @DisplayName("it should be thrown an exception if contact not exists when edit contact")
    @Transactional
    void shouldBeThrownAnExceptionIfContactNotExistsWhenEditContact() {
        Assertions.assertThrows(InvalidContactException.class, () -> {
            final Contact contact = ContactMockData.createContactToEdit();
            contact.setId(0);
            repository.editContact(contact);
        });
    }
    @Test
    @DisplayName("it should be thrown an exception if contact not exists when remove contact")
    @Transactional
    void shouldBeThrownAnExceptionIfContactNotExistsWhenRemoveContact() {
        Assertions.assertThrows(InvalidContactException.class, () -> {
            final Contact contact = ContactMockData.createContactToEdit();
            contact.setId(0);
            repository.editContact(contact);
        });
    }
    @Test
    @DisplayName("it should not be returned contact when remove contact")
    @Transactional
    void shouldNotBeReturnedContactWhenRemoveContact() {
        final Optional<Contact> createdContact = makeAddContact();
        if (createdContact.isEmpty()) {
            Assertions.fail("it couldn't be created contact");
            return;
        }
        final Contact contact = createdContact.get();
        final long id = contact.getId();
        repository.removeContact(id);
        MatcherAssert.assertThat(repository.findContactById(id), Matchers.nullValue());
    }
    @Test
    @DisplayName("it should be returned favorite contacts")
    void shouldBeReturnedFavoriteContacts() {
        final List<Contact> contacts = repository.getFavoriteContacts();
        MatcherAssert.assertThat(contacts, Matchers.notNullValue());
    }
    @Test
    @DisplayName("it should be switched favorite contact when toggle favorite contact")
    @Transactional
    void shouldBeSwitchedFavoriteContact() {
        final Optional<Contact> createdContact = makeAddContact();
        if (createdContact.isEmpty()) {
            Assertions.fail("it couldn't be created contact");
            return;
        }
        final Contact contact = createdContact.get();
        final long id = contact.getId();
        final boolean isFavorite = contact.getIsFavorite();
        repository.toggleFavoriteContact(id);
        MatcherAssert.assertThat(repository.findContactById(id).getIsFavorite(), Matchers.is(!isFavorite));
    }
}
