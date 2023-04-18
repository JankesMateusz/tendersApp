package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;
import com.jankes.tendersApp.purchasers.PurchaserMapper;
import com.jankes.tendersApp.purchasers.PurchaserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TenderService {

    private final TenderRepository tenderRepository;
    private final TenderItemRepository tenderItemRepository;
    private final TenderMapper mapper;
    private final TenderItemMapper tenderItemMapper;
    private final PurchaserService purchaserService;
    private final PurchaserMapper purchaserMapper;

    public TenderService(TenderRepository tenderRepository, TenderItemRepository tenderItemRepository, TenderMapper mapper, TenderItemMapper tenderItemMapper, PurchaserService purchaserService, PurchaserMapper purchaserMapper) {
        this.tenderRepository = tenderRepository;
        this.tenderItemRepository = tenderItemRepository;
        this.mapper = mapper;
        this.tenderItemMapper = tenderItemMapper;
        this.purchaserService = purchaserService;
        this.purchaserMapper = purchaserMapper;
    }


    public TenderDto findSingleTender(Long id) {
        return tenderRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new IllegalStateException("Tender not found"));
    }

    public List<TenderDto> findAllTendersForPurchaser(Long id) {
        return tenderRepository.findAllByPurchaserId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TenderDto> findAllTendersByTitle(String phrase) {
        return tenderRepository.findAllByTitleIgnoreCaseContaining(phrase)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    TenderDto saveTender(TenderDto dtoToSave, Long purchaserId, List<TenderItemDto> items) {
        var toSave = mapper.toEntity(dtoToSave);
        var purchaser = purchaserMapper.toEntity(purchaserService.findPurchaser(purchaserId));

        if (tenderRepository.existsByBidNumber(toSave.getBidNumber())) {
            var result = updateTender(tenderRepository.findByBidNumber(toSave.getBidNumber()));
            return mapper.toDto(result);
        }
        toSave.setPurchaser(purchaser);
        toSave.setPersonOfContactFirstName(purchaser.getPersonOfContactFirstName());
        toSave.setPersonOfContactLastName(purchaser.getPersonOfContactLastName());
        toSave.setEmail(purchaser.getEmail());
        toSave.setPhoneNumber(purchaser.getPhoneNumber());

        var result = tenderRepository.save(toSave);
        var tenderItems = items.stream().map(tenderItemMapper::toEntity).toList();

        tenderItems.forEach(t -> {
            t.setTender(result);
            tenderItemRepository.save(t);
        });

        return mapper.toDto(result);
    }

    private Tender updateTender(Tender toSave) {
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
            itemsToRemove.forEach(toRemove -> {
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
                if (item.getTender() == null) {
                    item.setTender(toSave);
                }
            });
            return tenderRepository.save(toSave);
        });
    }

    public List<Tender> findTendersByReportDate(LocalDate date) {
        return tenderRepository.findAllByReportDate(date);
    }

    public List<Tender> findTendersBetweenReportDates(LocalDate start, LocalDate end) {
        return tenderRepository.findAllByReportDateGreaterThanEqualAndReportDateLessThanEqual(start, end);
    }
}
