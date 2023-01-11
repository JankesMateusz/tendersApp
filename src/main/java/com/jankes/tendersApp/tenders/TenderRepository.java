package com.jankes.tendersApp.tenders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TenderRepository {

    List<Tender> findAllByPurchaserId(Long id);

    boolean existsById(Long id);

    Tender save(Tender entity);

    Optional<Tender> findById(Long id);

    List<Tender> findAllByTitleIgnoreCaseContaining(String phrase);

    List<Tender> findAllByReportDate(LocalDate date);

    List<Tender> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
