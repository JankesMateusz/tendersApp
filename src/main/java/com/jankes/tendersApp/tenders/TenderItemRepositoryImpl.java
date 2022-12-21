package com.jankes.tendersApp.tenders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderItemRepositoryImpl extends TenderItemRepository, JpaRepository<TenderItem, Long> {
}
