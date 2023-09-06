package net.personalprojects.contactbook.controller;

import net.personalprojects.contactbook.common.ResponseAPI;
import net.personalprojects.contactbook.model.ContactCategory;
import net.personalprojects.contactbook.service.ContactCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseAPI> getContactCategories() {
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", contactCategoryService.getContactCategories()));
    }
}
