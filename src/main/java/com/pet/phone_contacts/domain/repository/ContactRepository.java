package com.pet.phone_contacts.domain.repository;

import com.pet.phone_contacts.domain.model.entity.Contact;
import com.pet.phone_contacts.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(User user);
    Optional<Contact> findByName(String name);
    Optional<Contact> findById(Long id);
}
