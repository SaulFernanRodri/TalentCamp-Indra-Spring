package com.example.talent.unit;

import com.example.talent.entity.Contact;
import com.example.talent.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testSaveContact() {
        Contact contact = new Contact(null, "Alice", "Wonderland", "alice@example.com", "555123456", true);
        Contact savedContact = contactRepository.save(contact);

        assertNotNull(savedContact.getId(), "El ID del contacto no debería ser nulo después de guardar");
        assertEquals("Alice", savedContact.getFirstName(), "El nombre del contacto debería ser Alice");
    }

    @Test
    void testFindContactById() {
        Contact contact = new Contact(null, "Bob", "Builder", "bob@example.com", "555987654", false);
        contact = contactRepository.save(contact);

        Optional<Contact> foundContact = contactRepository.findById(contact.getId());
        assertTrue(foundContact.isPresent(), "El contacto debería existir en la base de datos");
        assertEquals("Bob", foundContact.get().getFirstName(), "El nombre del contacto debería ser Bob");
    }

    @Test
    void testFindAllContacts() {
        Contact contact1 = new Contact(null, "Charlie", "Brown", "charlie@example.com", "555111222", false);
        Contact contact2 = new Contact(null, "Daisy", "Duck", "daisy@example.com", "555333444", true);

        contactRepository.save(contact1);
        contactRepository.save(contact2);

        List<Contact> contacts = contactRepository.findAll();
        assertEquals(2, contacts.size(), "Deberían existir 2 contactos en la base de datos");
    }

    @Test
    void testDeleteContactById() {
        Contact contact = new Contact(null, "Eve", "Johnson", "eve@example.com", "555555555", false);
        contact = contactRepository.save(contact);

        contactRepository.deleteById(contact.getId());
        Optional<Contact> deletedContact = contactRepository.findById(contact.getId());

        assertTrue(deletedContact.isEmpty(), "El contacto debería haber sido eliminado de la base de datos");
    }
}

