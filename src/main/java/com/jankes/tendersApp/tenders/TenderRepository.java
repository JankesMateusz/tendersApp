package com.jankes.tendersApp.tenders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TenderRepository {

    long count();

    List<Tender> findAllByPurchaserId(Long id);

    boolean existsByBidNumber(String bidNumber);

    Tender findByBidNumber(String bidNumber);

    Tender save(Tender entity);

    Optional<Tender> findById(Long id);

    List<Tender> findAllByTitleIgnoreCaseContaining(String phrase);

    List<Tender> findAllByReportDate(LocalDate date);

    List<Tender> findAllByReportDateGreaterThanEqualAndReportDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
