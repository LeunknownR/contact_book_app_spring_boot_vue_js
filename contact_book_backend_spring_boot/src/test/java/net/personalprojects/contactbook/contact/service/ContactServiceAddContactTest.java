package net.personalprojects.contactbook.contact.service;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.contact.utils.ContactTestHelper;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ContactServiceAddContactTest {
    @Autowired
    private ContactService service;
    @MockBean
    private ContactRepository repository;
    private void makeAddContact(final ResponseActionMessages responseActionMessage) {
        final Contact contact = ContactMockData.createContactToAdd();
        final AddContactForm addContactForm = new AddContactForm(ContactTestHelper.convertToContactDTOToAdd(contact));
        Mockito.when(repository.addContact(contact)).thenReturn(responseActionMessage);
        MatcherAssert.assertThat(service.addContact(addContactForm), Matchers.equalTo(responseActionMessage));
    }
    @Test
    @DisplayName("it should be returned SUCCESS message")
    void shouldBeReturnedSuccessMessage() {
        makeAddContact(ResponseActionMessages.SUCCESS);
    }
    @Test
    @DisplayName("it should be returned CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS message")
    void shouldBeReturnedContactNameOrEmailAlreadyExists() {
        makeAddContact(ResponseActionMessages.CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS);
    }
    @Test
    @DisplayName("it should be returned SOME_PHONES_ALREADY_EXIST message")
    void shouldBeReturnedSomePhonesAlreadyExistsMessage() {
        makeAddContact(ResponseActionMessages.SOME_PHONES_ALREADY_EXIST);
    }
    @Test
    @DisplayName("it should be returned ERROR message")
    void shouldBeReturnedErrorMessage() {
        makeAddContact(ResponseActionMessages.ERROR);
    }
}
