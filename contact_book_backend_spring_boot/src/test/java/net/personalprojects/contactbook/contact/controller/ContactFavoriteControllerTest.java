package net.personalprojects.contactbook.contact.controller;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.controller.ContactFavoriteController;
import net.personalprojects.contactbook.domain.contact.ContactId;
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

@WebMvcTest(ContactFavoriteController.class)
public class ContactFavoriteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService service;
    private ResultActions response;
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ContactFavoriteControllerListFavoritesTest {
        private List<Contact> contactFavoriteList;
        @BeforeAll
        void setup() throws Exception {
            contactFavoriteList = ContactMockData.createContactFavoriteList();
            Mockito.when(service.getFavoriteContacts()).thenReturn(contactFavoriteList);
            response = mockMvc.perform(
                    MockMvcRequestBuilders.get("/contacts/favorites")
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
                            .value(contactFavoriteList.size())
            );
        }
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ContactFavoriteControllerToggleFavoriteTest {
        @BeforeAll
        void setup() throws Exception {
            long contactIdLong = 1;
            final ContactId contactId = new ContactId(contactIdLong);
            Mockito.doNothing().when(service).toggleFavoriteContact(contactId);
            response = mockMvc.perform(
                    MockMvcRequestBuilders.patch("/contacts/favorites/toggle/{contactId}", contactIdLong)
                            .contentType(MediaType.APPLICATION_JSON)
            );
        }
        @Test
        @DisplayName("it should be returned ok http status")
        void shouldBeReturnedOkHttpStatus() throws Exception {
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @DisplayName("it should be returned a success message")
        void shouldBeReturnedSuccessMessage() throws Exception {
            response.andExpect(
                    MockMvcResultMatchers
                            .jsonPath("$.status")
                            .value(ResponseActionMessages.SUCCESS.toString())
            );
        }
    }
}
