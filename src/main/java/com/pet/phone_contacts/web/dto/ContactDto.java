package com.pet.phone_contacts.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    @NotBlank(message = "Name is required")
    private String name;

    private List<@Email(message = "Invalid email address") String> emails;

    private List<@Pattern(regexp = "\\+\\d{11}", message = "Invalid phone number") String> phones;

}
