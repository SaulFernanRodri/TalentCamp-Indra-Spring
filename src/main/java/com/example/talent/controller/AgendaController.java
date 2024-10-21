package com.example.talent.controller;

import com.example.talent.model.Contact;
import com.example.talent.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String home(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "index"; // Render the template src/main/resources/templates/index.html
    }

    @GetMapping("/contact/{id}")
    public String getContactById(@PathVariable Long id, Model model) {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            model.addAttribute("contact", contact.get());
            return "contact"; // Render the template src/main/resources/templates/contact.html
        } else {
            return "redirect:/agenda/"; // Redirect to home if contact not found
        }
    }

    @PostMapping("/contact")
    public String saveContact(@ModelAttribute Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/agenda/";
    }

    @DeleteMapping("/contact/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/agenda/";
    }
}