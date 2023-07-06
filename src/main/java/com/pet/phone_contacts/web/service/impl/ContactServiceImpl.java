package com.pet.phone_contacts.web.service.impl;

import com.pet.phone_contacts.domain.model.entity.Contact;
import com.pet.phone_contacts.domain.model.entity.Email;
import com.pet.phone_contacts.domain.model.entity.PhoneNumber;
import com.pet.phone_contacts.domain.model.entity.User;
import com.pet.phone_contacts.domain.repository.ContactRepository;
import com.pet.phone_contacts.domain.repository.EmailRepository;
import com.pet.phone_contacts.domain.repository.PhoneNumberRepository;
import com.pet.phone_contacts.domain.repository.UserRepository;
import com.pet.phone_contacts.web.dto.ContactDto;
import com.pet.phone_contacts.web.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository, EmailRepository emailRepository, PhoneNumberRepository phoneNumberRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }
    
    private User getUserFromSecurityContextHolder() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
    }

    @Override
    public List<ContactDto> getAllContacts() {
        User user = getUserFromSecurityContextHolder();
        List<Contact> contacts = contactRepository.findByUser(user);

        List<ContactDto> response = new ArrayList<>();

        for (Contact contact : contacts) {
            List<String> emails = contact.getEmails().stream()
                    .map(Email::getEmail)
                    .collect(Collectors.toList());

            List<String> phones = contact.getPhones().stream()
                    .map(PhoneNumber::getPhoneNumber)
                    .collect(Collectors.toList());

            ContactDto contactResponseDto = new ContactDto(contact.getName(), emails, phones);

            response.add(contactResponseDto);
        }

        return response;
    }

    @Override
    public Contact createContact(ContactDto contactDto) {

        Contact contact = Contact.builder()
                .name(contactDto.getName())
                .user(getUserFromSecurityContextHolder())
                .build();

        List<Email> emails = contactDto.getEmails().stream()
                .map(email -> Email.builder()
                        .email(email)
                        .contact(contact).build())
                .collect(Collectors.toList());
        contact.setEmails(emails);

        List<PhoneNumber> phoneNumbers = contactDto.getPhones().stream()
                .map(phone -> PhoneNumber.builder()
                        .phoneNumber(phone)
                        .contact(contact).build())
                .collect(Collectors.toList());
        contact.setPhones(phoneNumbers);

        Contact savedContact = contactRepository.save(contact);

        emailRepository.saveAll(emails);
        phoneNumberRepository.saveAll(phoneNumbers);

        return savedContact;
    }
}
