package com.example.talent.controller;

import com.example.talent.entity.Contact;
import com.example.talent.service.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactServiceImpl contactService;

    @Test
    void testGetAllContacts() throws Exception {
        Contact contact1 = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);
        Contact contact2 = new Contact(2L, "Jane", "Smith", "jane@example.com", "987654321", true);

        when(contactService.getAllContacts()).thenReturn(Arrays.asList(contact1, contact2));

        mockMvc.perform(get("/api/v1/contacts")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("talent", "talent"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    void testGetContactById() throws Exception {
        Contact contact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);

        when(contactService.getContactById(1L)).thenReturn(Optional.of(contact));

        mockMvc.perform(get("/api/v1/contacts/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("talent", "talent"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testSaveContact() throws Exception {
        Contact contact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);

        when(contactService.saveContact(any(Contact.class))).thenReturn(contact);

        mockMvc.perform(post("/api/v1/contacts")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("talent", "talent"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"john@example.com\", \"phoneNumber\":\"123456789\", \"favourite\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testUpdateContact() throws Exception {
        Contact existingContact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);
        Contact updatedContact = new Contact(1L, "John", "Doe", "john.doe@example.com", "987654321", true);

        when(contactService.getContactById(1L)).thenReturn(Optional.of(existingContact));
        when(contactService.updateContact(any(Contact.class))).thenReturn(updatedContact);

        mockMvc.perform(put("/api/v1/contacts/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("talent", "talent"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"john.doe@example.com\", \"phoneNumber\":\"987654321\", \"favourite\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("987654321"))
                .andExpect(jsonPath("$.favourite").value(true));
    }

    @Test
    void testDeleteContact() throws Exception {
        Contact contact = new Contact(1L, "John", "Doe", "john@example.com", "123456789", false);

        when(contactService.getContactById(1L)).thenReturn(Optional.of(contact));

        mockMvc.perform(delete("/api/v1/contacts/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("talent", "talent"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(contactService, times(1)).deleteContact(contact.getId());
    }
}
