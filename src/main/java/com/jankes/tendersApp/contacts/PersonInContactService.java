package com.jankes.tendersApp.contacts;

import com.jankes.tendersApp.purchasers.PurchaserMapper;
import com.jankes.tendersApp.purchasers.PurchaserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonInContactService {

    private final PersonInContactRepository repository;
    private final PersonInContactMapper mapper;
    private final PurchaserService purchaserService;
    private final PurchaserMapper purchaserMapper;

    public PersonInContactService(PersonInContactRepository repository, PersonInContactMapper mapper, PurchaserService purchaserService, PurchaserMapper purchaserMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.purchaserService = purchaserService;
        this.purchaserMapper = purchaserMapper;
    }

    public PersonInContactDto findPersonInContact(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new IllegalStateException("Person not found"));

    }

    public List<PersonInContactDto> findAllForPurchaser(Long id){
        return repository.findAllByPurchaserId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonInContactDto createPersonInContact(PersonInContactDto toCreate, Long id){

        var toSave = mapper.toEntity(toCreate);
        var purchaser = purchaserMapper.toEntity(purchaserService.findPurchaser(id));
        toSave.setPurchaser(purchaser);
        var result = repository.save(toSave);
        return mapper.toDto(result);
    }

//    private PersonInContact updatePersonInContact(PersonInContact personInContact){
//
//    }
}
