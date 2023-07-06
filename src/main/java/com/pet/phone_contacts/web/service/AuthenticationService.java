package com.pet.phone_contacts.web.service;

import com.pet.phone_contacts.web.dto.AuthRequestDto;
import com.pet.phone_contacts.web.dto.SignUpDto;

public interface AuthenticationService {

    void registration(SignUpDto signUpDto);

    String login(AuthRequestDto authRequestDto);

    void createUser(SignUpDto signUpDto);
}
