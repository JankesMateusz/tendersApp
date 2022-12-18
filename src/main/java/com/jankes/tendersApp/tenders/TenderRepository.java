package com.jankes.tendersApp.tenders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TenderRepository extends JpaRepository<Tender, Long> {

    List<Tender> findAllByPurchaserId(Long id);

    boolean existsById(Long id);
}
