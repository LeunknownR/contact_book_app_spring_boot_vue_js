package net.personalprojects.contactbook.repository;

import net.personalprojects.contactbook.model.ContactCategory;

import java.util.List;

public interface ContactCategoryRepository {
    List<ContactCategory> getContactCategories();
}
