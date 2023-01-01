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
    private final TenderMapper mapper;

    public TenderService(TenderRepository tenderRepository, TenderItemRepository tenderItemRepository, TenderMapper mapper){
        this.tenderRepository = tenderRepository;
        this.tenderItemRepository = tenderItemRepository;
        this.mapper = mapper;
    }


    public TenderDto findSingleTender(Long id){
        return tenderRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new IllegalStateException("Tender not found"));
    }

    public List<TenderDto> findAllTendersForPurchaser(Long id){
        return tenderRepository.findAllByPurchaserId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TenderDto> findAllTendersByTitle(String phrase){
        return tenderRepository.findAllByTitleIgnoreCaseContaining(phrase)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    //TODO tender status? status update?
    TenderDto saveTender(TenderDto dtoToSave) throws Exception {
        var toSave = mapper.toEntity(dtoToSave);
        if(tenderRepository.existsById(toSave.getId())){
            var result = updateTender(toSave);
            return mapper.toDto(result);
        }
        var result = tenderRepository.save(toSave);
        return mapper.toDto(result);
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
