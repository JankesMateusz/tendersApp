package com.jankes.tendersApp.tenders;

import org.springframework.data.jpa.repository.JpaRepository;

interface TenderItemRepository extends JpaRepository<TenderItem, Long> {

}
