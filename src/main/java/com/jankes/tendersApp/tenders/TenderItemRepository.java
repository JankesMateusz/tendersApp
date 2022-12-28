package com.jankes.tendersApp.tenders;

import java.util.Optional;

interface TenderItemRepository {

    Optional<TenderItem> findById(Long id);

    TenderItem save(TenderItem entity);

    void delete(TenderItem toRemove);
}
