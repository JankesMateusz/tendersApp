package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderService {

    private final TenderRepository tenderRepository;
    private final TenderItemRepository tenderItemRepository;
    private final TenderFactory tenderFactory;

    public TenderService(TenderRepository tenderRepository, TenderItemRepository tenderItemRepository, TenderFactory tenderFactory){
        this.tenderRepository = tenderRepository;
        this.tenderItemRepository = tenderItemRepository;
        this.tenderFactory = tenderFactory;
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
    TenderDto saveTender(TenderDto dtoToSave){
        var toSave = tenderFactory.from(dtoToSave);
        if(tenderRepository.findById(toSave.getId()).isPresent()){
            return updateTender(toSave).toDto();
        }
        return tenderRepository.save(toSave).toDto();
    }

    Tender updateTender(Tender toSave){
        return null; //TODO
    }

}
