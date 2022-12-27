package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Repository;

import java.util.Optional;

interface TenderItemRepository {

    Optional<TenderItem> findById(Long id);

    TenderItem save(TenderItem entity);


    void delete(TenderItem toRemove);
}
