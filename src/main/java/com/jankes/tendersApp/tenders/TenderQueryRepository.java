package com.jankes.tendersApp.tenders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TenderQueryRepository extends JpaRepository<Tender, Long> {

    List<Tender> findAllByReportDate(LocalDate date);

    List<Tender> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
