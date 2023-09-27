package net.personalprojects.contactbook.contactcategory.utils;

import net.personalprojects.contactbook.model.ContactCategory;

import java.util.List;

public class ContactCategoryMockData {
    public static List<ContactCategory> createContactCategoryList() {
        return List.of(
                ContactCategory.builder()
                        .id("AMG")
                        .name("Amigos")
                    .build()
        );
    }
}
