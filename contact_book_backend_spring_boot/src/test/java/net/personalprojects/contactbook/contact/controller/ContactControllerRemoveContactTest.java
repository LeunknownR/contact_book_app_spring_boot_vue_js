package net.personalprojects.contactbook.contact.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.controller.ContactController;
import net.personalprojects.contactbook.domain.contact.ContactId;
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

@WebMvcTest(ContactController.class)
public class ContactControllerRemoveContactTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService service;
    @Autowired
    private ObjectMapper objectMapper;
    private ResultActions response;
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Returns a success message")
    class ReturnSuccessMessage {
        @BeforeAll
        void setup() throws Exception {
            final long contactIdLong = 1;
            final ContactId contactId = new ContactId(contactIdLong);
            Mockito.doNothing().when(service).removeContact(contactId);
            response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/contacts/remove/{contactId}", contactIdLong)
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
