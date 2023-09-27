package net.personalprojects.contactbook.contact.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.controller.ContactController;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.model.ContactPhone;
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

import java.util.stream.Collectors;

@WebMvcTest(ContactController.class)
public class ContactControllerEditContactTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService service;
    @Autowired
    private ObjectMapper objectMapper;
    private ResultActions response;
    private void makeRequest(final ContactDTO contactDTO) throws Exception {
        response = mockMvc.perform(
            MockMvcRequestBuilders.put("/contacts/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactDTO))
        );
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Returns a success message")
    class ReturnSuccessMessage {
        private final ResponseActionMessages RESPONSE_ACTION_MESSAGE = ResponseActionMessages.SUCCESS;
        private Contact editedContact;
        @BeforeAll
        void setup() throws Exception {
            final ContactDTO contactDTO = ContactMockData.createContactDTOForEdition();
            final EditContactForm editContactForm = new EditContactForm(contactDTO);
            editedContact = Contact.builder()
                    .id(contactDTO.getId())
                    .name(String.format("%s Lopez", contactDTO.getName()))
                    .email(contactDTO.getEmail())
                    .category(
                            ContactCategory.builder()
                                    .id(contactDTO.getCategoryId())
                                    .name("Trabajo")
                                    .build())
                    .isFavorite(contactDTO.getIsFavorite())
                    .phones(contactDTO.getPhonesToEdit().stream().map((contactPhone) ->
                            ContactPhone.builder()
                                    .id(contactPhone.getId())
                                    .number(contactPhone.getNumber())
                                    .build()
                    ).collect(Collectors.toSet()))
                    .build();
            Mockito.when(service.editContact(editContactForm)).thenReturn(RESPONSE_ACTION_MESSAGE);
            Mockito.when(service.findContactById(editContactForm.id())).thenReturn(editedContact);
            makeRequest(contactDTO);
        }
        @Test
        @DisplayName("it should be returned ok http status")
        void shouldBeReturnedOkHttpStatus() throws Exception {
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @DisplayName("it should be returned edited contact as data")
        void shouldBeReturnedEditedContactAsData() throws Exception {
            response.andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.data")
                    .exists()
            );
        }
        @Test
        @DisplayName("it should be returned a success message")
        void shouldBeReturnedSuccessMessage() throws Exception {
            response.andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.status")
                    .value(RESPONSE_ACTION_MESSAGE.toString())
            );
        }
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Returns an error message")
    class ReturnErrorMessage {
        private final ResponseActionMessages RESPONSE_ACTION_MESSAGE = ResponseActionMessages.SOME_PHONES_ALREADY_EXIST;
        @BeforeAll
        void setup() throws Exception {
            final ContactDTO contactDTO = ContactMockData.createContactDTOForEdition();
            final EditContactForm editContactForm = new EditContactForm(contactDTO);
            Mockito.when(service.editContact(editContactForm)).thenReturn(RESPONSE_ACTION_MESSAGE);
            makeRequest(contactDTO);
        }
        @Test
        @DisplayName("it should be returned bad request http status")
        void shouldBeReturnedBadRequestHttpStatus() throws Exception {
            response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
        @Test
        @DisplayName("it should be returned null data")
        void shouldBeReturnedNullData() throws Exception {
            response.andExpect(
                    MockMvcResultMatchers
                            .jsonPath("$.data")
                            .doesNotExist()
            );
        }
        @Test
        @DisplayName("it should be returned an error message")
        void shouldBeReturnedErrorMessage() throws Exception {
            response.andExpect(
                    MockMvcResultMatchers
                            .jsonPath("$.status")
                            .value(RESPONSE_ACTION_MESSAGE.toString())
            );
        }
    }
}
