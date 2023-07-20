package com.jankes.tendersApp.tenders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TenderRepository {

    long count();

    List<Tender> findAll();

    List<Tender> findAllByPurchaserId(Long id);

    Tender save(Tender entity);

    Optional<Tender> findByMdpId(String mdpId);

    boolean existsByMdpId(String mdpId);

    boolean existsById(Long id);

    List<Tender> findAllByTitleIgnoreCaseContaining(String phrase);

    List<Tender> findAllByReportDate(LocalDate date);

    List<Tender> findAllByReportDateGreaterThanEqualAndReportDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
