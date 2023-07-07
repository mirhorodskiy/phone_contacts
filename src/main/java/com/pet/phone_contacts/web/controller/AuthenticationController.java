package com.pet.phone_contacts.web.controller;

import com.pet.phone_contacts.web.dto.AuthRequestDto;
import com.pet.phone_contacts.web.dto.SignUpDto;
import com.pet.phone_contacts.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        authenticationService.registration(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequestDto request) throws LoginException {

        String token = authenticationService.login(request);
        if (token == null)
            throw new LoginException("Invalid email/password combination");

        return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
