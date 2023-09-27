package net.personalprojects.contactbook.contact.domain;

import net.personalprojects.contactbook.domain.contact.ContactEmail;
import net.personalprojects.contactbook.domain.contact.ContactName;
import net.personalprojects.contactbook.domain.contactcategory.ContactCategoryId;
import net.personalprojects.contactbook.exception.InvalidContactException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ContactFormTest {
    @Nested
    @DisplayName("for contact name")
    class ContactNameTest {
        @Test
        @DisplayName("it should be instantiated if it's not empty string and its length is less than or equal to 50")
        void shouldBeInstantiatedIfItsNotEmptyStringAndItsLengthIsLessThanOrEqualTo50() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactName("Manuel");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactName(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's empty string")
        void shouldBeThrownAnExpectionIfItsEmptyString() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactName("");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is greater than 50")
        void shouldBeThrownAnExpectionIfItsLenghtIsGreaterThan50() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactName("A".repeat(51));
            });
        }
    }
    @Nested
    @DisplayName("for contact email")
    class ContactEmailTest {
        @Test
        @DisplayName("it should be instantiated if it's not empty string and its length is less than or equal to 30")
        void shouldBeInstantiatedIfItsNotEmptyStringAndItsLengthIsLessThanOrEqualTo30() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactEmail("mrivera@gmail.com");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactEmail(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is less than or equal to 5")
        void shouldBeThrownAnExpectionIfItsEmptyString() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactEmail("a");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is greater than 30")
        void shouldBeThrownAnExpectionIfItsLenghtIsGreaterThan30() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactEmail("A".repeat(31));
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it doesn't have a valid format")
        void shouldBeThrownAnExpectionIfItDoesntHaveAValidFormat() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactEmail("male.ribeaxd");
            });
        }
    }
    @Nested
    @DisplayName("for contact category id")
    class ContactCategoryIdTest {
        @Test
        @DisplayName("it should be instantiated if it's its length is equal to 30")
        void shouldBeInstantiatedIfItsNotEmptyStringAndItsLengthIsEqualTo30() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactCategoryId("TRB");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactCategoryId(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is less than 3")
        void shouldBeThrownAnExpectionIfItsLenghtIsLessThan3() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactCategoryId("TR");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is greater than 3")
        void shouldBeThrownAnExpectionIfItsLenghtIsGreaterThan3() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactCategoryId("TRBA");
            });
        }
    }
}
