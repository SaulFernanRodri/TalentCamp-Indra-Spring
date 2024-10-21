package com.example.talent.service;

import com.example.talent.entity.Contact;
import com.example.talent.repository.ContactRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    @Transactional
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
