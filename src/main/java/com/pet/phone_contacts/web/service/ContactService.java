package com.pet.phone_contacts.web.service;


import com.pet.phone_contacts.domain.model.entity.Contact;
import com.pet.phone_contacts.web.dto.ContactDto;

import java.util.List;

public interface ContactService {

    List<ContactDto> getAllContacts();

    Contact createContact(ContactDto contactDto);
}
