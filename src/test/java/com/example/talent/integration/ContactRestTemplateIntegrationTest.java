package com.example.talent.integration;

import com.example.talent.entity.Contact;
import com.example.talent.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class ContactRestTemplateIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {

        restTemplate = restTemplate.withBasicAuth("talent", "talent");
        contactRepository.deleteAll();
    }

    @Test
    void testCreateContact() {
        Contact contact = new Contact(null, "Alice", "Wonderland", "alice@example.com", "555123456", true);
        ResponseEntity<Contact> response = restTemplate.postForEntity("/api/v1/contacts", contact, Contact.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Alice", response.getBody().getFirstName());
    }

    @Test
    void testGetAllContacts() {
        contactRepository.save(new Contact(null, "Alice", "Wonderland", "alice@example.com", "555123456", true));
        contactRepository.save(new Contact(null, "Bob", "Builder", "bob@example.com", "555987654", false));

        ResponseEntity<Contact[]> response = restTemplate.getForEntity("/api/v1/contacts", Contact[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    void testUpdateContact() {
        Contact contact = contactRepository.save(new Contact(null, "David", "Smith", "david@example.com", "555111222", false));
        Contact updatedContactInfo = new Contact(contact.getId(), "David", "Smith", "david.updated@example.com", "555999888", true);

        restTemplate.put("/api/v1/contacts/" + contact.getId(), updatedContactInfo);

        ResponseEntity<Contact> response = restTemplate.getForEntity("/api/v1/contacts/" + contact.getId(), Contact.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("david.updated@example.com", response.getBody().getEmail());
    }

    @Test
    void testDeleteContact() {
        Contact contact = contactRepository.save(new Contact(null, "Eve", "Johnson", "eve@example.com", "555333444", false));

        restTemplate.delete("/api/v1/contacts/" + contact.getId());

        ResponseEntity<Contact> response = restTemplate.getForEntity("/api/v1/contacts/" + contact.getId(), Contact.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
