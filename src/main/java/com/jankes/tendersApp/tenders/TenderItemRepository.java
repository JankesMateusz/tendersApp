package com.jankes.tendersApp.tenders;

import java.util.List;
import java.util.Optional;

interface TenderItemRepository {

    Optional<TenderItem> findById(Long id);

    List<TenderItem> findByTenderMdpId(String mdpId);

    TenderItem save(TenderItem entity);

    void delete(TenderItem toRemove);
}
