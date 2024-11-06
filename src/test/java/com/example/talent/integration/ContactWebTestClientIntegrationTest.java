/*package com.example.talent.integration;

import com.example.talent.entity.Contact;
import com.example.talent.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*Para WebFlux, se utiliza WebTestClient en lugar de TestRestTemplate.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:application-test.properties")
class ContactWebTestClientIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    void testCreateContact() {
        Contact contact = new Contact(null, "Alice", "Wonderland", "alice@example.com", "555123456", true);

        webTestClient.post()
                .uri("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(contact)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Contact.class)
                .value(responseContact -> {
                    assertNotNull(responseContact.getId());
                    assertTrue(responseContact.getFirstName().equals("Alice"));
                });
    }

    @Test
    void testGetAllContacts() {
        contactRepository.save(new Contact(null, "Alice", "Wonderland", "alice@example.com", "555123456", true));
        contactRepository.save(new Contact(null, "Bob", "Builder", "bob@example.com", "555987654", false));

        webTestClient.get()
                .uri("/api/v1/contacts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Contact.class)
                .hasSize(2);
    }

    @Test
    void testUpdateContact() {
        Contact contact = contactRepository.save(new Contact(null, "David", "Smith", "david@example.com", "555111222", false));
        Contact updatedContactInfo = new Contact(null, "David", "Smith", "david.updated@example.com", "555999888", true);

        webTestClient.put()
                .uri("/api/v1/contacts/" + contact.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedContactInfo)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Contact.class)
                .value(responseContact -> {
                    assertTrue(responseContact.getEmail().equals("david.updated@example.com"));
                    assertTrue(responseContact.getPhoneNumber().equals("555999888"));
                });
    }

    @Test
    void testDeleteContact() {
        Contact contact = contactRepository.save(new Contact(null, "Eve", "Johnson", "eve@example.com", "555333444", false));

        webTestClient.delete()
                .uri("/api/v1/contacts/" + contact.getId())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri("/api/v1/contacts/" + contact.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
}*/