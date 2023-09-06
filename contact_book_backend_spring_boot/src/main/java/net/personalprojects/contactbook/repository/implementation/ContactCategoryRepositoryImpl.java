package net.personalprojects.contactbook.repository.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.repository.ContactCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactCategoryRepositoryImpl implements ContactCategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ContactCategory> getContactCategories() {
        String query = "FROM ContactCategory";
        List<ContactCategory> contactCategoryList = entityManager
                .createQuery(query, ContactCategory.class)
                .getResultList();
        return contactCategoryList;
    }
}
