package com.example.talent.controller;

import com.example.talent.entity.Contact;
import com.example.talent.service.ContactServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    private final ContactServiceImpl contactService;

    @Autowired
    public AgendaController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/view")
    public String showContactsPage(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("agenda", contacts);
        return "Agenda";
    }
}