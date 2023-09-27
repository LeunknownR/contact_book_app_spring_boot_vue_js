package net.personalprojects.contactbook.contact.domain;

import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneId;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneList;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneNumber;
import net.personalprojects.contactbook.dto.ContactPhoneDTO;
import net.personalprojects.contactbook.exception.InvalidContactException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class EditContactFormTest {
    @Nested
    @DisplayName("for contact id")
    class ContactIdTest {
        @Test
        @DisplayName("it should be instantiated if it's greater than 0")
        void shouldBeInstantiatedIfItsGreaterThan0() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactId(1);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's 0")
        void shouldBeThrownAnExpectionIfIts0() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactId(0);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's a negative number")
        void shouldBeThrownAnExpectionIfItsANegativeNumber() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactId(-1);
            });
        }
    }
    @Nested
    @DisplayName("for contact phone id")
    class ContactPhoneIdTest {
        @Test
        @DisplayName("it should be instantiated if it's greater than 0")
        void shouldBeInstantiatedIfItsGreaterThan0() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactPhoneId(1L);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's 0")
        void shouldBeThrownAnExpectionIfIts0() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneId(0L);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's a negative number")
        void shouldBeThrownAnExpectionIfItsANegativeNumber() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneId(-1L);
            });
        }
    }
    @Nested
    @DisplayName("for contact phone number")
    class ContactPhoneNumberTest {
        @Test
        @DisplayName("it should be instantiated if its length is 9")
        void shouldBeInstantiatedIfItsLengthIs9() {
            Assertions.assertDoesNotThrow(() -> {
                new ContactPhoneNumber("9".repeat(9));
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneNumber(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is less than 9")
        void shouldBeThrownAnExpectionIfItsLenghtIsLessThan9() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneNumber("9".repeat(8));
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if its length is greater than 9")
        void shouldBeThrownAnExpectionIfItsLenghtIsGreaterThan9() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneNumber("9".repeat(10));
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's not a integer")
        void shouldBeThrownAnExpectionIfItsNotAInteger() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneNumber("A".repeat(9));
            });
        }
    }
    @Nested
    @DisplayName("for contact phone list")
    class ContactPhoneListTest {
        @Test
        @DisplayName("it should be instantiated if there are between 1 and 3 phones")
        void shouldBeInstantiatedIfThereAreBetween1And3Phones() {
            Assertions.assertDoesNotThrow(() -> {
                final Set<ContactPhoneDTO> setOfContactPhoneDTOs = ContactMockData.createContactPhoneDTOs();
                new ContactPhoneList(setOfContactPhoneDTOs);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneList(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if there are not phones")
        void shouldBeThrownAnExpectionIfThereAreNotPhones() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneList(Set.of());
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if there are more than 3 phones")
        void shouldBeThrownAnExpectionIfThereAreMoreThan3Phones() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                final Set<ContactPhoneDTO> setOfContactPhoneDTOs = ContactMockData.createContactPhoneDTOsWithMoreThan3Phones();
                new ContactPhoneList(setOfContactPhoneDTOs);
            });
        }
    }
}
