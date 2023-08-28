package net.personalprojects.contactbook.controller;

import jakarta.validation.Valid;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.service.ContactService;
import net.personalprojects.contactbook.common.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @GetMapping(value = "/all")
    public ResponseEntity<ResponseAPI> getAllContacts(@Valid ContactFilters filters) {
        final List<ContactDTO> allContacts = contactService.getAllContacts(filters);
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", allContacts));
    }
    @PostMapping(value = "/add")
    public ResponseEntity<ResponseAPI> addContact(@RequestBody ContactDTO contactDTO) {
        final AddContactForm addContactForm = new AddContactForm(contactDTO);
        contactService.addContact(addContactForm);
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", null));
    }
    @PutMapping(value = "/edit")
    public ResponseEntity<ResponseAPI> editContact(@RequestBody ContactDTO contactDTO) {
        final EditContactForm editContactForm = new EditContactForm(contactDTO);
        contactService.editContact(editContactForm);
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", null));
    }
    @DeleteMapping(value = "/remove/{contactId}")
    public ResponseEntity<ResponseAPI> removeContact(@PathVariable long contactIdParam) {
        final ContactId contactId = new ContactId(contactIdParam);
        contactService.removeContact(contactId);
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", null));
    }
    @GetMapping(value = "/favorites")
    public ResponseEntity<ResponseAPI> getFavoriteContacts() {
        final List<ContactDTO> favoriteContacts = contactService.getFavoriteContacts();
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", favoriteContacts));
    }
    @PatchMapping(value = "/favorites/toggle/{contactId}")
    public ResponseEntity<ResponseAPI> toggleFavoriteContact(@PathVariable long contactIdParam) {
        final ContactId contactId = new ContactId(contactIdParam);
        contactService.toggleFavoriteContact(contactId);
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", null));
    }
}
