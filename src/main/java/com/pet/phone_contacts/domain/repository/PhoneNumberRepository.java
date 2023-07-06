package com.pet.phone_contacts.domain.repository;

import com.pet.phone_contacts.domain.model.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

}
