package net.personalprojects.contactbook.contact.service;

import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.service.ContactService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactServiceListContactsTest {
    @Autowired
    private ContactService service;
    @MockBean
    private ContactRepository repository;
    private List<Contact> contactList;
    @BeforeAll
    void setup() {
        contactList = ContactMockData.createContactList();
    }
    @Test
    @DisplayName("it should be returned the list")
    void shouldBeReturnedTheList() {
        final ContactFilters contactFilters = ContactMockData.createFilters();
        Mockito.when(
                repository.getAllContacts(
                        contactFilters.contactName(),
                        contactFilters.contactPhoneNumber()
                )).thenReturn(contactList);
        MatcherAssert.assertThat(service.getAllContacts(contactFilters), Matchers.equalTo(contactList));
    }
}
