package com.jankes.tendersApp.tenders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface TenderRepository {

    List<Tender> findAllByPurchaserId(Long id);
    boolean existsById(Long id);

    Tender save(Tender entity);

    Optional<Tender> findById(Long id);
}
