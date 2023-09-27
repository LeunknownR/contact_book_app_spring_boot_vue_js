package net.personalprojects.contactbook.contactcategory.controller;

import net.personalprojects.contactbook.contactcategory.utils.ContactCategoryMockData;
import net.personalprojects.contactbook.controller.ContactCategoryController;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.service.ContactCategoryService;
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

@WebMvcTest(ContactCategoryController.class)
public class ContactCategoryControllerTest {
    @MockBean
    private ContactCategoryService service;
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("when a request is made to list contact categories")
    class GetContactCategoryListTest {
        private List<ContactCategory> contactCategories;
        @BeforeAll
        void setup() throws Exception {
            contactCategories = ContactCategoryMockData.createContactCategoryList();
            Mockito.when(service.getContactCategories()).thenReturn(contactCategories);
            response = mockMvc.perform(
                MockMvcRequestBuilders
                    .get("/contact-categories")
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
                    .value(contactCategories.size())
            );
        }
    }
}
