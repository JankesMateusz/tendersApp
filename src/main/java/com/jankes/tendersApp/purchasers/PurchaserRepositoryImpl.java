package com.jankes.tendersApp.purchasers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaserRepositoryImpl extends PurchaserRepository, JpaRepository<Purchaser, Long> {
}
