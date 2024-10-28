package com.example.talent.controller;

import com.example.talent.entity.Contact;
import com.example.talent.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactServiceImpl contactService;

    @Autowired
    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact.get(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> saveContact(@Valid @RequestBody Contact contact) {
        Contact nuevoContact = contactService.saveContact(contact);
        URI uri = createURIContact(nuevoContact);
        return ResponseEntity.created(uri).body(nuevoContact);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, @Valid @RequestBody Contact contact) {
        Optional<Contact> contactOptional = contactService.getContactById(id);

        if (contactOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Contact existingContact = contactOptional.get();

        existingContact.setFirstName(contact.getFirstName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setFavourite(contact.getFavourite());

        Contact updatedContact = contactService.updateContact(existingContact);

        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contactService.deleteContact(contact.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createURIContact(Contact contact) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(contact.getId()).toUri();
    }
}
