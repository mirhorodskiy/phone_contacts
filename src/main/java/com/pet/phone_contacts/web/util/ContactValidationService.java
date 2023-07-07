package com.pet.phone_contacts.web.util;

import com.pet.phone_contacts.domain.model.error.ValidationException;
import com.pet.phone_contacts.web.dto.ContactDto;
import org.springframework.stereotype.Service;

@Service
public class ContactValidationService {

    public void validateContactDto(ContactDto contactDto) throws ValidationException {
        if (contactDto == null) {
            throw new ValidationException("Contact is null");
        }

        if (contactDto.getName() == null) {
            throw new ValidationException("Name is required");
        }

        if (contactDto.getEmails() == null || contactDto.getEmails().isEmpty()) {
            throw new ValidationException("At least one email is required");
        }

        for (String email : contactDto.getEmails()) {
            if (!isValidEmail(email)) {
                throw new ValidationException("Invalid email: " + email);
            }
        }

        if (contactDto.getPhones() == null || contactDto.getPhones().isEmpty()) {
            throw new ValidationException("At least one phone number is required");
        }

        for (String phone : contactDto.getPhones()) {
            if (!isValidPhoneNumber(phone)) {
                throw new ValidationException("Invalid phone number: " + phone);
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "\\+\\d{12}";
        return phoneNumber.matches(phoneRegex);
    }
}
