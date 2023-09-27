package net.personalprojects.contactbook.contact.service;

import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.contact.utils.ContactTestHelper;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class ContactServiceFavoriteContactsTest {
    @Autowired
    private ContactService service;
    @MockBean
    private ContactRepository repository;
    @Nested
    class ListFavoriteContactsTest {
        @Test
        @DisplayName("it should be returned the list")
        void shouldBeReturnedTheList() {
            final List<Contact> contacts = ContactMockData.createContactFavoriteList();
            Mockito.when(repository.getFavoriteContacts()).thenReturn(contacts);
            MatcherAssert.assertThat(service.getFavoriteContacts(), Matchers.equalTo(contacts));
        }
    }
    @Nested
    class ToggleContactFavoriteTest {
        @Test
        @DisplayName("it should be execute remove contact in repository if contact exists")
        void shouldBeExecuteRemoveContactInRepositoryIfContactExists() {
            final long contactIdLong = 1;
            final ContactId contactId = new ContactId(1);
            Mockito.doNothing().when(repository).toggleFavoriteContact(contactIdLong);
            Assertions.assertDoesNotThrow(() -> service.toggleFavoriteContact(contactId));
        }
        @Test
        @DisplayName("it should be thrown an exception if contact not exists")
        void shouldBeThrownAnExceptionIfContactNotExists() {
            final long contactIdLong = 1;
            final ContactId contactId = new ContactId(1);
            Mockito.doThrow(new InvalidContactException("Contact not exists")).when(repository).toggleFavoriteContact(contactIdLong);
            Assertions.assertThrows(InvalidContactException.class, () -> service.toggleFavoriteContact(contactId));
        }
    }
}
