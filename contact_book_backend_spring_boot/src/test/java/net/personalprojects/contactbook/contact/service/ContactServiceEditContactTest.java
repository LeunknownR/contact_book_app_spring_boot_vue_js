package net.personalprojects.contactbook.contact.service;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.contact.utils.ContactTestHelper;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ContactServiceEditContactTest {
    @Autowired
    private ContactService service;
    @MockBean
    private ContactRepository repository;
    private void makeEditContactWithMessage(final ResponseActionMessages responseActionMessage) {
        final Contact contact = ContactMockData.createContactToEdit();
        final EditContactForm editContactForm = new EditContactForm(ContactTestHelper.convertToContactDTOToEdit(contact));
        Mockito.when(repository.editContact(contact)).thenReturn(responseActionMessage);
        MatcherAssert.assertThat(service.editContact(editContactForm), Matchers.equalTo(responseActionMessage));
    }
    @Test
    @DisplayName("it should be returned SUCCESS message if it's successfully")
    void shouldBeReturnedSuccessMessageIfItsSuccessfully() {
        makeEditContactWithMessage(ResponseActionMessages.SUCCESS);
    }
    @Test
    @DisplayName("it should be returned SOME_PHONES_ALREADY_EXIST message if there is an error")
    void shouldBeReturnedSomePhonesAlreadyExistsMessageIfThereIsAnError() {
        makeEditContactWithMessage(ResponseActionMessages.SOME_PHONES_ALREADY_EXIST);
    }
    @Test
    @DisplayName("it should be returned CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS message if there is an error")
    void shouldBeReturnedContactNameOrEmailAlreadyExistsIfThereIsAnError() {
        makeEditContactWithMessage(ResponseActionMessages.CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS);
    }
    @Test
    @DisplayName("it should be returned ERROR message if there is an error")
    void shouldBeReturnedErrorMessageIfThereIsAnError() {
        makeEditContactWithMessage(ResponseActionMessages.ERROR);
    }
    @Test
    @DisplayName("it should be thrown an exception if contact not exists")
    void shouldBeThrownAnExceptionIfContactNotExists() {
        final Contact contact = ContactMockData.createContactToEdit();
        final EditContactForm editContactForm = new EditContactForm(ContactTestHelper.convertToContactDTOToEdit(contact));
        Mockito.when(repository.editContact(contact)).thenThrow(new InvalidContactException("Contact to edit not exists"));
        Assertions.assertThrows(InvalidContactException.class, () -> service.editContact(editContactForm));
    }
}
