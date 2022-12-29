package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
                .orElseThrow(
                        () -> new IllegalStateException("Tender not found"));
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
        if(tenderRepository.existsById(toSave.getId())){
            return updateTender(toSave).toDto();
        }
        return tenderRepository.save(toSave).toDto();
    }

    private Tender updateTender(Tender toSave){
        return tenderRepository.findById(toSave.getId()).map(existingTender -> {
            Set<TenderItem> itemsToRemove = new HashSet<>();
            existingTender.setLink(toSave.getLink());
            existingTender.setPublicationDate(toSave.getPublicationDate());
            existingTender.setBidDate(toSave.getBidDate());
            existingTender.setTitle(toSave.getTitle());
            existingTender.getTenderItems()
                    .forEach(existingItem -> toSave.getTenderItems().stream()
                            .filter(potentialOverride -> existingItem.getId().equals(toSave.getId()))
                            .findFirst()
                            .ifPresentOrElse(
                                    overrideItem -> {
                                        existingItem.setCategory(overrideItem.getCategory());
                                        existingItem.setQuantity(overrideItem.getQuantity());
                                        existingItem.setOs(overrideItem.getOs());
                                        existingItem.setOffice(overrideItem.getOffice());
                                        existingItem.setRemarks(overrideItem.getRemarks());
                                        existingItem.setTaskNumber(overrideItem.getTaskNumber());
                                    },
                                    () -> itemsToRemove.add(existingItem)
                            )
                    );
            itemsToRemove.forEach(toRemove ->{
                existingTender.removeTenderItem(toRemove);
                tenderItemRepository.delete(toRemove);
            });
            toSave.getTenderItems().stream()
                    .filter(newItem -> existingTender.getTenderItems().stream()
                            .noneMatch(existingItem -> existingItem.getId().equals(newItem.getId())))
                    .collect(Collectors.toSet())
                    .forEach(existingTender::addTenderItem);
            tenderRepository.save(existingTender);
            return existingTender;
        }).orElseGet(() -> {
            toSave.getTenderItems().forEach(item -> {
                if(item.getTender() == null){
                    item.setTender(toSave);
                }
            });
            return tenderRepository.save(toSave);
        });
    }

}
