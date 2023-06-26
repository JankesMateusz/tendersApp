package com.jankes.tendersApp.contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInContactRepositoryImpl extends PersonInContactRepository, JpaRepository<PersonInContact, Long> {
}
