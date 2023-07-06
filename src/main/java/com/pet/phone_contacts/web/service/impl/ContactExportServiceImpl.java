package com.pet.phone_contacts.web.service.impl;

import com.pet.phone_contacts.web.dto.ContactDto;
import com.pet.phone_contacts.web.service.ContactExportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactExportServiceImpl implements ContactExportService {

    @Override
    public String generateCsvData(List<ContactDto> contacts) {
        StringBuilder csvData = new StringBuilder();

        csvData.append("Name,Email,Phone\n");

        for (ContactDto contact : contacts) {
            csvData.append(contact.getName()).append(",")
                    .append(String.join(",", contact.getEmails())).append(",")
                    .append(String.join(",", contact.getPhones())).append("\n");
        }

        return csvData.toString();
    }
}

