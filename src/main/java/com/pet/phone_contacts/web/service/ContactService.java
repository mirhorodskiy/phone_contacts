package com.pet.phone_contacts.web.service;


import com.pet.phone_contacts.domain.model.entity.Contact;
import com.pet.phone_contacts.web.dto.ContactDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContactService {

    List<ContactDto> getAllContacts();

    Contact createContact(ContactDto contactDto);

    Contact editContact(ContactDto contactDto, Long contactId);

    void deleteContact(Long contactId);

    void uploadContactImage(Long contactId, MultipartFile image);

    byte[] getContactImage(Long contactId);
}
