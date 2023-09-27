package net.personalprojects.contactbook.controller;

import net.personalprojects.contactbook.common.ResponseAPI;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactFavoriteController {
    @Autowired
    private ContactService contactService;
    @GetMapping(value = "/favorites")
    public ResponseEntity<ResponseAPI> getFavoriteContacts() {
        final List<Contact> favoriteContacts = contactService.getFavoriteContacts();
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", favoriteContacts));
    }
    @PatchMapping(value = "/favorites/toggle/{contactId}")
    public ResponseEntity<ResponseAPI> toggleFavoriteContact(@PathVariable long contactId) {
        contactService.toggleFavoriteContact(new ContactId(contactId));
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", null));
    }
}
