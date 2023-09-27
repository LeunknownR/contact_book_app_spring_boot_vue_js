package net.personalprojects.contactbook.contact.service;

import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ContactServiceRemoveContactTest {
    @Autowired
    private ContactService service;
    @MockBean
    private ContactRepository repository;
    @Test
    @DisplayName("it should be execute remove contact in repository if contact exists")
    void shouldBeExecuteRemoveContactInRepositoryIfContactExists() {
        final long contactIdLong = 1;
        final ContactId contactId = new ContactId(1);
        Mockito.doNothing().when(repository).removeContact(contactIdLong);
        Assertions.assertDoesNotThrow(() -> service.removeContact(contactId));
    }
    @Test
    @DisplayName("it should be thrown an exception if contact not exists")
    void shouldBeThrownAnExceptionIfContactNotExists() {
        final long contactIdLong = 1;
        final ContactId contactId = new ContactId(1);
        Mockito.doThrow(new InvalidContactException("Contact not exists")).when(repository).removeContact(contactIdLong);
        Assertions.assertThrows(InvalidContactException.class, () -> service.removeContact(contactId));
    }
}
