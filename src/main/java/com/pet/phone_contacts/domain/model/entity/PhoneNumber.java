package com.pet.phone_contacts.domain.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @Column(nullable = false)
    private String phoneNumber;
}
