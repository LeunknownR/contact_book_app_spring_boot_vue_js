package net.personalprojects.contactbook.contactcategory.repository;

import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.repository.ContactCategoryRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContactCategoryRepositoryTest {
    @Autowired
    private ContactCategoryRepository repository;
    @Test
    @DisplayName("it should be returned contact categories")
    void shouldBeReturnedContactCategories() {
        final List<ContactCategory> contactCategories = repository.getContactCategories();
        MatcherAssert.assertThat(contactCategories, Matchers.notNullValue());
    }
}
