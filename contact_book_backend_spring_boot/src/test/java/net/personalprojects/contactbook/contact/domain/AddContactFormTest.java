package net.personalprojects.contactbook.contact.domain;

import net.personalprojects.contactbook.contact.utils.ContactMockData;
import net.personalprojects.contactbook.contact.utils.ContactTestHelper;
import net.personalprojects.contactbook.domain.contactphone.ContactPhoneNumberList;
import net.personalprojects.contactbook.exception.InvalidContactException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class AddContactFormTest {
    @Nested
    @DisplayName("for contact phone number list")
    class ContactPhoneNumberListTest {
        @Test
        @DisplayName("it should be instantiated if there are between 1 and 3 phones")
        void shouldBeInstantiatedIfThereAreBetween1And3Phones() {
            Assertions.assertDoesNotThrow(() -> {
                final Set<String> setOfContactPhoneNumbers = ContactTestHelper.convertToSetOfPhoneNumbersString(ContactMockData.createContactPhoneDTOsWith3Phones());
                new ContactPhoneNumberList(setOfContactPhoneNumbers);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if it's null")
        void shouldBeThrownAnExpectionIfItsNull() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneNumberList(null);
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if there are not phones")
        void shouldBeThrownAnExpectionIfThereAreNotPhones() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                new ContactPhoneNumberList(Set.of());
            });
        }
        @Test
        @DisplayName("it should be thrown an expection if there are more than 3 phones")
        void shouldBeThrownAnExpectionIfThereAreMoreThan3Phones() {
            Assertions.assertThrows(InvalidContactException.class, () -> {
                final Set<String> setOfContactPhoneNumbers = ContactTestHelper.convertToSetOfPhoneNumbersString(ContactMockData.createContactPhoneDTOsWithMoreThan3Phones());
                new ContactPhoneNumberList(setOfContactPhoneNumbers);
            });
        }
    }
}
