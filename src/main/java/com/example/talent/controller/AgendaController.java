package com.example.talent.controller;

import com.example.talent.exception.ResourceNotFoundException;
import com.example.talent.entity.Contact;
import com.example.talent.service.ContactService;

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
@RequestMapping("/agenda")
public class AgendaController {

    private final ContactService contactService;

    @Autowired
    public AgendaController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isEmpty()) {
            throw new ResourceNotFoundException("Contact not found");
        } else {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> saveContact(@Valid @RequestBody Contact contact) {
        Contact nuevoContact = contactService.saveContact(contact);
        URI uri = createURIContact(nuevoContact);

        return ResponseEntity.created(uri).body(nuevoContact);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") Long id) {
        Optional<Contact> contact = contactService.getContactById(id);

        if (contact.isEmpty()) {
            throw new ResourceNotFoundException("Contacto no encontrado");
        } else {
            contactService.deleteContact(contact.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, @Valid @RequestBody Contact contact) {
        Optional<Contact> contactOptional = contactService.getContactById(id);

        if (contactOptional.isEmpty()) {
            throw new ResourceNotFoundException("Contacto no encontrado");
        } else {
            Contact nuevoContact = contactService.updateContact(contact);
            return new ResponseEntity<>(nuevoContact, HttpStatus.OK);
        }
    }

    private URI createURIContact(Contact contact) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(contact.getId()).toUri();
    }

}