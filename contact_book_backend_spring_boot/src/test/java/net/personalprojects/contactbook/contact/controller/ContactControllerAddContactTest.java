package net.personalprojects.contactbook.contact.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.controller.ContactController;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.service.ContactService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ContactController.class)
public class ContactControllerAddContactTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService service;
    private ResultActions response;
    @Autowired
    private ObjectMapper objectMapper;
    private void makeRequest(final ResponseActionMessages responseActionMessage) throws Exception {
        final ContactDTO contactDTO = ContactMockData.createContactDTOForAdding();
        final AddContactForm addContactForm = new AddContactForm(contactDTO);
        Mockito.when(service.addContact(addContactForm)).thenReturn(responseActionMessage);
        response = mockMvc.perform(
            MockMvcRequestBuilders.post("/contacts/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactDTO))
        ).andDo(MockMvcResultHandlers.print());
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Returns a success message")
    class ReturnSuccessMessage {
        private final ResponseActionMessages RESPONSE_ACTION_MESSAGE = ResponseActionMessages.SUCCESS;
        @BeforeAll
        void setup() throws Exception {
            makeRequest(RESPONSE_ACTION_MESSAGE);
        }
        @Test
        @DisplayName("it should be returned ok http status")
        void shouldBeReturnedOkHttpStatus() throws Exception {
            response.andExpect(MockMvcResultMatchers.status().isOk());
        }
        @Test
        @DisplayName("it should be returned null data")
        void shouldBeReturnedNullData() throws Exception {
            response.andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.data")
                    .value(Matchers.nullValue())
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
            makeRequest(RESPONSE_ACTION_MESSAGE);
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
                    .value(Matchers.nullValue())
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
