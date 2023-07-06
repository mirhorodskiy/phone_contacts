package com.pet.phone_contacts.web.service.impl;

import com.pet.phone_contacts.domain.model.entity.User;
import com.pet.phone_contacts.domain.model.enums.Role;
import com.pet.phone_contacts.domain.model.error.SighUpException;
import com.pet.phone_contacts.domain.repository.UserRepository;
import com.pet.phone_contacts.web.dto.AuthRequestDto;
import com.pet.phone_contacts.web.dto.SignUpDto;
import com.pet.phone_contacts.web.security.JwtTokenProvider;
import com.pet.phone_contacts.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void registration(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail()))
            throw new SighUpException("Email is already exists", HttpStatus.FORBIDDEN);
        else if (userRepository.existsByUsername(signUpDto.getUsername()))
            throw new SighUpException("Username is already exists", HttpStatus.FORBIDDEN);

        createUser(signUpDto);
    }

    @Override
    public String login(AuthRequestDto credits) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credits.getUsername(), credits.getPassword()));

        User user = userRepository.findByUsername(credits.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return jwtTokenProvider.createToken(credits.getUsername(), user.getRole().name());
    }

    @Override
    public void createUser(SignUpDto signUpDto) {
        User user = User.builder()
                .email(signUpDto.getEmail())
                .username(signUpDto.getUsername())
                .role(Role.USER)
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        userRepository.save(user);
    }
}
