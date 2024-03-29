package net.personalprojects.contactbook.controller;

import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.domain.contact.EditContactForm;
import net.personalprojects.contactbook.domain.contact.AddContactForm;
import net.personalprojects.contactbook.domain.contact.ContactId;
import net.personalprojects.contactbook.dto.ContactDTO;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.service.ContactService;
import net.personalprojects.contactbook.common.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/contacts",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ContactController {
    @Autowired
    private ContactService contactService;
    @GetMapping(value = "/all")
    public ResponseEntity<ResponseAPI> getAllContacts(ContactFilters filters) {
        final List<Contact> allContacts = contactService.getAllContacts(filters);
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", allContacts));
    }
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseAPI> addContact(@RequestBody final ContactDTO contactDTO) {
        final AddContactForm addContactForm = new AddContactForm(contactDTO);
        final ResponseActionMessages message = contactService.addContact(addContactForm);
        if (message == ResponseActionMessages.SUCCESS)
            return ResponseEntity.ok(
                new ResponseAPI(message.toString(), null)
            );
        return ResponseEntity.badRequest().body(
            new ResponseAPI(message.toString(), null)
        );
    }
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseAPI> editContact(@RequestBody final ContactDTO contactDTO) {
        final EditContactForm editContactForm = new EditContactForm(contactDTO);
        final ResponseActionMessages message = contactService.editContact(editContactForm);
        if (message == ResponseActionMessages.SUCCESS)
            return ResponseEntity.ok(
                new ResponseAPI(message.toString(), contactService.findContactById(contactDTO.getId()))
            );
        return ResponseEntity.badRequest().body(
                new ResponseAPI(message.toString(), null)
        );
    }
    @DeleteMapping(value = "/remove/{contactId}")
    public ResponseEntity<ResponseAPI> removeContact(@PathVariable long contactId) {
        contactService.removeContact(new ContactId(contactId));
        return ResponseEntity.ok(new ResponseAPI("SUCCESS", null));
    }
}
