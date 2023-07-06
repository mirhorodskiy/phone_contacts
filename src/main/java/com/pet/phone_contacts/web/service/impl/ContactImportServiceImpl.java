package com.pet.phone_contacts.web.service.impl;

import com.pet.phone_contacts.domain.model.error.FileProcessingException;
import com.pet.phone_contacts.web.dto.ContactDto;
import com.pet.phone_contacts.web.service.ContactImportService;
import com.pet.phone_contacts.web.service.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactImportServiceImpl implements ContactImportService {

    private final ContactService contactService;

    public ContactImportServiceImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void importContactsFromFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            skipHeaderLine(reader);

            List<ContactDto> contactDtos = readContactsFromCsv(reader);

            saveContacts(contactDtos);

        } catch (IOException e) {
            throw new FileProcessingException("Failed to process contact import file", e);
        }
    }

    private void skipHeaderLine(BufferedReader reader) throws IOException {
        reader.readLine(); // Пропускаємо перший рядок, що містить заголовки стовпців
    }

    private List<ContactDto> readContactsFromCsv(BufferedReader reader) throws IOException {
        List<ContactDto> contactDtos = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String name = fields[0].trim();
            String[] emails = fields[1].trim().split(",");
            String[] phones = Arrays.copyOfRange(fields, 2, fields.length);

            ContactDto contactDto = createContactDto(name, emails, phones);

            contactDtos.add(contactDto);
        }

        return contactDtos;
    }

    private ContactDto createContactDto(String name, String[] emails, String[] phones) {
        ContactDto contactDto = new ContactDto();
        contactDto.setName(name);
        contactDto.setEmails(Arrays.asList(emails));
        contactDto.setPhones(Arrays.asList(phones));
        return contactDto;
    }

    private void saveContacts(List<ContactDto> contactDtos) {
        for (ContactDto contactDto : contactDtos) {
            contactService.createContact(contactDto);
        }
    }
}






