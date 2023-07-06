package com.pet.phone_contacts.web.dto;

import com.pet.phone_contacts.domain.model.entity.Email;
import com.pet.phone_contacts.domain.model.entity.PhoneNumber;
import com.pet.phone_contacts.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private String name;

    private List<String> emails;

    private List<String> phones;

}
