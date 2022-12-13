package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderService {

    private final TenderRepository tenderRepository;

    public TenderService(TenderRepository tenderRepository){
        this.tenderRepository = tenderRepository;
    }


    public TenderDto findSingleTender(Long id){
        return tenderRepository.findById(id)
                .map(TenderDto::new)
                .orElseThrow(() -> new IllegalStateException("Tender not found"));
    }

    public List<TenderDto> findAllTendersForPurchaser(Long id){
        return tenderRepository.findAllByPurchaserId(id)
                .stream()
                .map(TenderDto::new)
                .collect(Collectors.toList());
    }
}
