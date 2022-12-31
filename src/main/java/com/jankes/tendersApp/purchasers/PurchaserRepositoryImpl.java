package com.jankes.tendersApp.purchasers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PurchaserRepositoryImpl extends PurchaserRepository, JpaRepository<Purchaser, Long> {
}
