package com.pet.phone_contacts.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface ContactImportService {

    void importContactsFromFile(MultipartFile file);
}
