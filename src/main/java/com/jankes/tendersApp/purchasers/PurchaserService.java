package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.tenders.Tender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaserService {

    private final PurchaserRepository purchaserRepository;
    private final PurchaserMapper mapper;

    public PurchaserService(PurchaserRepository purchaserRepository, PurchaserMapper mapper){
        this.purchaserRepository = purchaserRepository;
        this.mapper = mapper;
    }

    public PurchaserDto findPurchaser(Long id){
        return purchaserRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new IllegalStateException("Purchaser not found"));
    }

    public List<PurchaserDto> findAllPurchasers(){
        return purchaserRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PurchaserDto> findByName(String name){
        return purchaserRepository.findByOfficialNameIgnoreCaseContaining(name)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PurchaserDto createPurchaser(PurchaserDto toCreate){
        var toSave = mapper.toEntity(toCreate);

        if(purchaserRepository.existsById(toCreate.getId())){
            var result = updatePurchaser(toSave);
            return mapper.toDto(result);
        }

        var result = purchaserRepository.save(toSave);
        return mapper.toDto(result);
    }

    private Purchaser updatePurchaser(Purchaser toUpdate){
        return purchaserRepository.findById(toUpdate.getId())
                .map(existingPurchaser -> {
                    existingPurchaser.setCity(toUpdate.getCity());
                    existingPurchaser.setProvince(toUpdate.getProvince());
                    existingPurchaser.setAddress(toUpdate.getAddress());
                    existingPurchaser.setOfficialName(toUpdate.getOfficialName());
                    existingPurchaser.setPersonOfContactFirstName(toUpdate.getPersonOfContactFirstName());
                    existingPurchaser.setPersonOfContactLastName(toUpdate.getPersonOfContactLastName());
                    existingPurchaser.setEmail(toUpdate.getEmail());
                    existingPurchaser.setPhoneNumber(toUpdate.getPhoneNumber());
                    existingPurchaser.setZipCode(toUpdate.getZipCode());
                    existingPurchaser.setTypeOfAccount(toUpdate.getTypeOfAccount());
                    purchaserRepository.save(existingPurchaser);
                    return existingPurchaser;
                }).orElseThrow(() -> {
                    throw new IllegalStateException("Purchaser not found");
                });
    }

    public void addTender(Tender toAdd){
        purchaserRepository.findById(toAdd.getPurchaser().getId()).ifPresent(purchaser -> purchaser.addTender(toAdd));
    }

    public void removeTender(Tender toRemove){
        purchaserRepository.findById(toRemove.getPurchaser().getId()).ifPresent(purchaser -> removeTender(toRemove));
    }
}
