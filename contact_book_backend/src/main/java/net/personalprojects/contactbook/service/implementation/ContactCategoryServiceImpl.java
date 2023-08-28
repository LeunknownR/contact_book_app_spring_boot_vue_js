package net.personalprojects.contactbook.service.implementation;

import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.repository.ContactCategoryRepository;
import net.personalprojects.contactbook.service.ContactCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactCategoryServiceImpl implements ContactCategoryService {
    @Autowired
    private ContactCategoryRepository contactCategoryRepository;
    public List<ContactCategory> getContactCategories() {
        return contactCategoryRepository.getContactCategories();
    }
}
