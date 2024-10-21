package com.example.talent.service;

import com.example.talent.entity.Contact;
import java.util.List;
import java.util.Optional;

public interface IContactService {
    public List<Contact> getAllContacts();
    public Optional<Contact> getContactById(Long id);
    public Contact saveContact(Contact contact);
    public Contact updateContact(Contact contact);
    public void deleteContact(Long id);
}
