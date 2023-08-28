package net.personalprojects.contactbook.controller;

import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.service.ContactCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/contact-categories")
public class ContactCategoryController {
    @Autowired
    private ContactCategoryService contactCategoryService;
    @GetMapping
    public List<ContactCategory> getContactCategories() {
        return contactCategoryService.getContactCategories();
    }
}
