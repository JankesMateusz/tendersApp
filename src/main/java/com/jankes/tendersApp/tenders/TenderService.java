package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.contacts.PersonInContactMapper;
import com.jankes.tendersApp.contacts.PersonInContactService;
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
    private final PersonInContactService personInContactService;
    private final PersonInContactMapper personInContactMapper;

    public TenderService(TenderRepository tenderRepository, TenderItemRepository tenderItemRepository, TenderMapper mapper, TenderItemMapper tenderItemMapper, PurchaserService purchaserService, PurchaserMapper purchaserMapper, PersonInContactService personInContactService, PersonInContactMapper personInContactMapper) {
        this.tenderRepository = tenderRepository;
        this.tenderItemRepository = tenderItemRepository;
        this.mapper = mapper;
        this.tenderItemMapper = tenderItemMapper;
        this.purchaserService = purchaserService;
        this.purchaserMapper = purchaserMapper;
        this.personInContactService = personInContactService;
        this.personInContactMapper = personInContactMapper;
    }

    public TenderDto findSingleTender(String mdpId) {
        return tenderRepository.findByMdpId(mdpId)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new IllegalStateException("Tender not found"));
    }

    public List<TenderDto> findAllTenders(){
        return tenderRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
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
    TenderDto saveTender(TenderDto dtoToSave, Long purchaserId, List<TenderItemDto> items, Long contactId) {
        var toSave = mapper.toEntity(dtoToSave);
        var purchaser = purchaserMapper.toEntity(purchaserService.findPurchaser(purchaserId));
        var tenderItems = items.stream().map(tenderItemMapper::toEntity).toList();
        var contact = personInContactMapper.toEntity(personInContactService.findPersonInContact(contactId));

        if (tenderRepository.existsByMdpId(toSave.getMdpId())) {
            var tender = tenderRepository.findByMdpId(toSave.getMdpId());
            toSave.setTenderItems(tenderItems);
            String mdpId;
            if(tender.isPresent()){
                mdpId = tender.get().getMdpId();
                var result = updateTender(toSave, mdpId);
                return mapper.toDto(result);
            }
        }

        toSave.setPurchaser(purchaser);
        toSave.setPersonInContact(contact);

        var result = tenderRepository.save(toSave);
        result.generateMDPID();

        tenderItems.forEach(t -> {
            t.setTender(result);
            tenderItemRepository.save(t);
            result.addTenderItem(t);
        });
        return mapper.toDto(result);
    }

    private Tender updateTender(Tender toSave, String mdpId){
        var itemsToRemove = tenderItemRepository.findByTenderMdpId(mdpId);
        itemsToRemove.forEach(t -> {
            tenderRepository.findByMdpId(mdpId).ifPresent(tender -> tender.removeTenderItem(t));
            tenderItemRepository.delete(t);
        });

        return tenderRepository.findByMdpId(mdpId).map(existingTender ->{
            existingTender.setBidNumber(toSave.getBidNumber());
            existingTender.setSiwzLink(toSave.getSiwzLink());
            existingTender.setPublicationDate(toSave.getPublicationDate());
            existingTender.setBidDate(toSave.getBidDate());
            existingTender.setTitle(toSave.getTitle());
            existingTender.setBudget(toSave.getBudget());
            toSave.getTenderItems().forEach(t -> {
                t.setTender(existingTender);
                tenderItemRepository.save(t);
                existingTender.addTenderItem(t);
            });
            return existingTender;
        }).orElseThrow(IllegalStateException::new);
    }

    //ToDO Delete?
    private Tender updateTender2(Tender toSave, String mdpId) {
        return tenderRepository.findByMdpId(mdpId).map(existingTender -> {

            Set<TenderItem> itemsToRemove = new HashSet<>();
            existingTender.setSiwzLink(toSave.getSiwzLink());
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
