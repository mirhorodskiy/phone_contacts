package com.pet.phone_contacts.web.service;

import com.pet.phone_contacts.web.dto.ContactDto;

import java.util.List;

public interface ContactExportService {
    String generateCsvData(List<ContactDto> contacts);
}
