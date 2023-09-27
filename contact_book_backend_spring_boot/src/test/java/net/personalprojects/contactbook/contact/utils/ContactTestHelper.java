package net.personalprojects.contactbook.contact.utils;

import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.dto.ContactPhoneDTO;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.model.ContactPhone;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Set;
import java.util.stream.Collectors;

public final class ContactTestHelper {
    public static MultiValueMap<String, String> convertToParams(final ContactFilters filters) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("contactName", filters.contactName());
        params.add("contactPhoneNumber", filters.contactPhoneNumber());
        return params;
    }
    public static Set<String> convertToSetOfPhoneNumbersString(final Set<ContactPhoneDTO> contactPhones) {
        return contactPhones
                .stream()
                .map(ContactPhoneDTO::getNumber).collect(Collectors.toSet());
    }
    public static ContactDTO convertToContactDTOToAdd(final Contact contact) {
        return ContactDTO
                .builder()
                    .name(contact.getName())
                    .email(contact.getEmail())
                    .categoryId(contact.getCategory().getId())
                    .isFavorite(contact.getIsFavorite())
                    .phonesToAdd(
                        contact
                            .getPhones()
                            .stream()
                            .map(ContactPhone::getNumber)
                            .collect(Collectors.toSet()))
                .build();
    }
    public static ContactDTO convertToContactDTOToEdit(final Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .categoryId(contact.getCategory().getId())
                .isFavorite(contact.getIsFavorite())
                .phonesToEdit(
                    contact.getPhones()
                        .stream()
                        .map((phone) -> ContactPhoneDTO
                            .builder()
                                .id(phone.getId())
                                .number(phone.getNumber())
                            .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
