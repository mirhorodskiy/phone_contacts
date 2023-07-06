package com.pet.phone_contacts.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {

    String email;
    String username;
    String password;

}
