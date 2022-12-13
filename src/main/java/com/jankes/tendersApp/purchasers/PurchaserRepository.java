package com.jankes.tendersApp.purchasers;

import org.springframework.data.jpa.repository.JpaRepository;

interface PurchaserRepository extends JpaRepository<Purchaser, Long> {
}
