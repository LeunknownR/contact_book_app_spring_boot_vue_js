package net.personalprojects.contactbook.contact.domain;

import net.personalprojects.contactbook.domain.contactfilters.ContactNameFilter;
import net.personalprojects.contactbook.domain.contactfilters.ContactPhoneNumberFilter;
import net.personalprojects.contactbook.exception.InvalidContactFiltersExpection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ContactFiltersTest {
    @Nested
    @DisplayName("for contact name filter")
    class ContactNameTest {
        @Test
        @DisplayName("it should be instantiated the filter if it's empty")
        void shouldBeInstantiatedIfItsEmptyString() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactNameFilter("");
            });
        }
        @Test
        @DisplayName("it should be instantiated the filter if it's not empty string and its length is less than or equal to 50")
        void shouldBeInstantiatedIfItsNotEmptyStringAndItsLengthIsLessThanOrEqualTo50() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactNameFilter("Manuel");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactFiltersExpection.class, () -> {
                new ContactNameFilter(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is greater than 50")
        void shouldBeThrownAnExpectionIfItsLenghtIsGreaterThan50() {
            Assertions.assertThrows(InvalidContactFiltersExpection.class, () -> {
                new ContactNameFilter("A".repeat(51));
            });
        }
    }
    @Nested
    @DisplayName("for contact phone number filter")
    class ContactPhoneNumberFilterTest {
        @Test
        @DisplayName("it should be instantiated the filter if it's empty")
        void shouldBeInstantiatedIfItsEmptyString() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactPhoneNumberFilter("");
            });
        }
        @Test
        @DisplayName("it should be instantiated the filter if it's not empty string and its length is less than or equal to 50")
        void shouldBeInstantiatedIfItsNotEmptyStringAndItsLengthIsLessThanOrEqualTo50() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactPhoneNumberFilter("900800700");
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is greater than 9")
        void shouldBeThrownAnExpectionWhenItsLenghtIsGreaterThan9() {
            Assertions.assertThrows(InvalidContactFiltersExpection.class, () -> {
                new ContactPhoneNumberFilter("9".repeat(10));
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactFiltersExpection.class, () -> {
                new ContactPhoneNumberFilter(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it doesn't contain only numbers")
        void shouldBeThrownAnExpectionIfItDoesntContainOnlyNumbers() {
            Assertions.assertThrows(InvalidContactFiltersExpection.class, () -> {
                new ContactPhoneNumberFilter("1".repeat(8) + "A");
            });
        }
    }
}
