package net.personalprojects.contactbook.contactcategory.service;

import net.personalprojects.contactbook.contactcategory.utils.ContactCategoryMockData;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.repository.ContactCategoryRepository;
import net.personalprojects.contactbook.service.ContactCategoryService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class ContactCategoryServiceTest {
    @Autowired
    private ContactCategoryService service;
    @MockBean
    private ContactCategoryRepository repository;
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ListContactCategories {
        private List<ContactCategory> contactCategoryList;
        @BeforeAll
        void setup() {
            contactCategoryList = ContactCategoryMockData.createContactCategoryList();
            Mockito.when(repository.getContactCategories()).thenReturn(contactCategoryList);
        }
        @Test
        @DisplayName("it should be returned the list")
        void shouldBeReturnedTheList() {
            MatcherAssert.assertThat(service.getContactCategories(), Matchers.equalTo(contactCategoryList));
        }
    }
}
