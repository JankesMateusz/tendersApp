package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderItemService {

    TenderItemRepository repository;
    TenderItemMapper mapper;

    public TenderItemService(TenderItemRepository repository, TenderItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TenderItemDto> findItemsByTenderMdpId(String mdpId) {
        return repository.findByTenderMdpId(mdpId).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
