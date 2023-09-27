package net.personalprojects.contactbook.contact.utils;

import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.dto.ContactPhoneDTO;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.model.ContactPhone;

import java.util.List;
import java.util.Set;

public class ContactMockData {
    private static Set<ContactPhone> createContactPhones() {
        return Set.of(
            ContactPhone
                .builder()
                    .id(1L)
                    .number("900800700")
                .build()
        );
    }
    private static Set<ContactPhone> createContactPhones2() {
        return Set.of(
                ContactPhone
                        .builder()
                        .id(2L)
                        .number("100200300")
                        .build()
        );
    }
    public static Contact createContact() {
        return Contact.builder()
                .id(1)
                .name("Carlos Sanchez")
                .email("csanchez@gmail.com")
                .category(
                        ContactCategory
                                .builder()
                                .id("AMG")
                                .name("Amigos")
                                .build()
                )
                .isFavorite(true)
                .phones(createContactPhones())
            .build();
    }
    public static Contact createContact2() {
        return Contact.builder()
                .id(2)
                .name("Diego Torres")
                .email("dtorres@gmail.com")
                .category(
                        ContactCategory
                                .builder()
                                .id("AMG")
                                .name("Amigos")
                                .build()
                )
                .isFavorite(true)
                .phones(createContactPhones2())
                .build();
    }
    public static Contact createContactToAdd(final Contact contact) {
        contact.setId(0);
        contact.getPhones().forEach((phone) -> {
            phone.setId(null);
            phone.setContact(contact);
        });
        contact.getCategory().setName(null);
        return contact;
    }
    public static Contact createContactToAdd() {
        return createContactToAdd(ContactMockData.createContact());
    }
    public static Contact createContactToEdit(final Contact contact) {
        contact.getCategory().setName(null);
        contact.getPhones().forEach((phone) -> {
            phone.setContact(contact);
        });
        return contact;
    }
    public static Contact createContactToEdit() {
        return createContactToEdit(ContactMockData.createContact());
    }
    public static List<Contact> createContactFavoriteList() {
        return createContactList().stream().peek((contact) -> contact.setIsFavorite(true)).toList();
    }
    public static List<Contact> createContactList() {
        return List.of(createContact());
    }
    public static ContactFilters createFilters() {
        return new ContactFilters("Manuel", "900");
    }
    public static ContactFilters createEmptyFilters() {
        return new ContactFilters("", "");
    }
    public static ContactDTO createContactDTOForAdding() {
        return ContactDTO.builder()
                .name("Rebecca Romero")
                .email("rromero@gmail.com")
                .categoryId("FML")
                .isFavorite(true)
                .phonesToAdd(Set.of("900800700"))
            .build();
    }
    public static Set<ContactPhoneDTO> createContactPhoneDTOs() {
        return Set.of(
            ContactPhoneDTO.builder()
                .id(1L)
                .number("900800700")
            .build(),
            ContactPhoneDTO.builder()
                .id(2L)
                .number("800700600")
            .build()
        );
    }
    public static Set<ContactPhoneDTO> createContactPhoneDTOsWith3Phones() {
        return Set.of(
            ContactPhoneDTO
                .builder()
                    .id(1L)
                    .number("900800700")
                .build(),
            ContactPhoneDTO
                .builder()
                    .id(2L)
                    .number("800700600")
                .build(),
            ContactPhoneDTO
                .builder()
                    .id(3L)
                    .number("700600500")
                .build()
        );
    }
    public static Set<ContactPhoneDTO> createContactPhoneDTOsWithMoreThan3Phones() {
        return Set.of(
                ContactPhoneDTO
                        .builder()
                        .id(1L)
                        .number("900800700")
                        .build(),
                ContactPhoneDTO
                        .builder()
                        .id(2L)
                        .number("800700600")
                        .build(),
                ContactPhoneDTO
                        .builder()
                        .id(3L)
                        .number("700600500")
                        .build(),
                ContactPhoneDTO
                        .builder()
                        .id(4L)
                        .number("600500400")
                        .build()
        );
    }
    public static ContactDTO createContactDTOForEdition() {
        return ContactDTO.builder()
                .id(11)
                .name("Carla Torres")
                .email("ctorres@outlook.com")
                .categoryId("TRB")
                .isFavorite(false)
                .phonesToEdit(createContactPhoneDTOs())
            .build();
    }

}
