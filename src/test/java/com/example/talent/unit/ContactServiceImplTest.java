package com.example.talent.unit;

import com.example.talent.entity.Contact;
import com.example.talent.repository.ContactRepository;
import com.example.talent.service.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-test.properties")
class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllContacts() {
        Contact contact1 = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);
        Contact contact2 = new Contact(2L, "Jane", "Smith", "jane@example.com", "987654321", true);
        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact1, contact2));

        List<Contact> contacts = contactService.getAllContacts();
        assertEquals(2, contacts.size());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    void testGetContactById() {
        Contact contact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Optional<Contact> foundContact = contactService.getContactById(1L);
        assertTrue(foundContact.isPresent());
        assertEquals("John", foundContact.get().getFirstName());
    }

    @Test
    void testSaveContact() {
        Contact contact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact savedContact = contactService.saveContact(contact);
        assertNotNull(savedContact);
        assertEquals("John", savedContact.getFirstName());
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    void testDeleteContact() {
        Contact contact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);
        contactService.deleteContact(contact.getId());
        verify(contactRepository, times(1)).deleteById(contact.getId());
    }
}
