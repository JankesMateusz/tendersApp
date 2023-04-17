package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.common.DtoMapper;
import com.jankes.tendersApp.tenders.TenderMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaserMapper implements DtoMapper<PurchaserDto, Purchaser>{

    @Override
    public PurchaserDto toDto(Purchaser entity) {
        return PurchaserDto.builder()
                .withId(Optional.ofNullable(entity.getId()).orElse(0L))
                .withOfficialName(entity.getOfficialName())
                .withCity(entity.getCity())
                .withProvince(entity.getProvince())
                .withAddress(entity.getAddress())
                .withZipCode(entity.getZipCode())
                .withPersonOfContactFirstName(entity.getPersonOfContactFirstName())
                .withPersonOfContactLastName(entity.getPersonOfContactLastName())
                .withPhone(entity.getPhoneNumber())
                .withTypeOfAccount(entity.getTypeOfAccount().name().toLowerCase())
                .withEmail(entity.getEmail())
                //.withTenders(Optional.ofNullable(entity.getTenders()).orElse(new HashSet<>()))
                .build();
    }

    @Override
    public Purchaser toEntity(PurchaserDto dto) {
        var result = new Purchaser();
        result.setId(dto.getId());
        result.setOfficialName(dto.getOfficialName());
        result.setCity(dto.getCity());
        result.setProvince(dto.getProvince());
        result.setAddress(dto.getAddress());
        result.setZipCode(dto.getZipCode());
        result.setPersonOfContactFirstName(dto.getPersonOfContactFirstName());
        result.setPersonOfContactLastName(dto.getPersonOfContactLastName());
        result.setPhoneNumber(dto.getPhone());
        result.setTypeOfAccount(TypeOfAccount.valueOf(dto.getTypeOfAccount().toUpperCase()));
        result.setEmail(dto.getEmail());
//        result.setTenders(
//                dto.getTenders()
//                .stream()
//                .map(t -> new TenderMapper().toEntity(t))
//                        .collect(Collectors.toSet()));
        return result;
    }
}
