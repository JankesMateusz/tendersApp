package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderService {

    private final TenderRepository tenderRepository;
    private final TenderItemRepository tenderItemRepository;

    public TenderService(TenderRepository tenderRepository, TenderItemRepository tenderItemRepository){
        this.tenderRepository = tenderRepository;
        this.tenderItemRepository = tenderItemRepository;
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

    //TODO tender status? status update?
//    public TenderDto saveTender(TenderDto toSave){
//        return tenderRepository.findById(toSave.getId())
//                .map()
//    }

    List<TenderItemDto> updateTenderItems(){

        return null;
    }

}
