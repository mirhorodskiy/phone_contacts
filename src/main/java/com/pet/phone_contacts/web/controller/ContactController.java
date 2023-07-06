package com.pet.phone_contacts.web.controller;

import com.pet.phone_contacts.domain.model.entity.Contact;
import com.pet.phone_contacts.web.dto.ContactDto;
import com.pet.phone_contacts.web.service.ContactService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactDto contact) {
        Contact createdContact = contactService.createContact(contact);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Contact> editContact(@RequestBody ContactDto contact, @RequestParam Long id) {
        Contact editedContact = contactService.editContact(contact, id);
        return new ResponseEntity<>(editedContact, HttpStatus.CREATED);
    }

    @DeleteMapping
    ResponseEntity<Void> deleteContact(@RequestParam Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{contactId}/image")
    public ResponseEntity<Void> uploadContactImage(@PathVariable Long contactId, @RequestParam("image") MultipartFile image) {
        contactService.uploadContactImage(contactId, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{contactId}/image")
    public ResponseEntity<byte[]> getContactImage(@PathVariable Long contactId) {
        byte[] image = contactService.getContactImage(contactId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
