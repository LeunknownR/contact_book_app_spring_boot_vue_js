package net.personalprojects.contactbook.contact.controller;

import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.contact.utils.ContactTestHelper;
import net.personalprojects.contactbook.controller.ContactController;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.service.ContactService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(ContactController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactControllerListContactsTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService service;
    private ResultActions response;
    private List<Contact> contactList;
    @BeforeAll
    void setup() throws Exception {
        contactList = ContactMockData.createContactList();
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("with empty filters")
    class ReturnWithEmptyFilters {
        @BeforeAll
        void setup() throws Exception {
            final ContactFilters filters = ContactMockData.createEmptyFilters();
            Mockito.when(service.getAllContacts(filters)).thenReturn(contactList);
            response = mockMvc.perform(
                    MockMvcRequestBuilders.get("/contacts/all")
                            .params(ContactTestHelper.convertToParams(filters))
                            .contentType(MediaType.APPLICATION_JSON)
            );
        }
        @Test
        @DisplayName("it should be returned ok http status")
        void shouldBeReturnedOkHttpStatus() throws Exception {
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @DisplayName("it should be returned the contact list")
        void shouldBeReturnedTheContactList() throws Exception {
            response.andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.data.size()")
                    .value(contactList.size())
            );
        }
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("with filled filters")
    class ReturnWithFilledFilters {
        @BeforeAll
        void setup() throws Exception {
            final ContactFilters filters = ContactMockData.createFilters();
            Mockito.when(service.getAllContacts(filters)).thenReturn(contactList);
            response = mockMvc.perform(
                MockMvcRequestBuilders.get("/contacts/all")
                    .params(ContactTestHelper.convertToParams(filters))
                    .contentType(MediaType.APPLICATION_JSON)
            );
        }
        @Test
        @DisplayName("it should be returned ok http status")
        void shouldBeReturnedOkHttpStatus() throws Exception {
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @DisplayName("it should be returned the contact list")
        void shouldBeReturnedTheContactList() throws Exception {
            response.andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.data.size()")
                    .value(contactList.size())
            );
        }
    }
}
