package com.pet.phone_contacts.domain.repository;

import com.pet.phone_contacts.domain.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
