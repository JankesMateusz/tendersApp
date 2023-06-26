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
                .withAddress(entity.getAddress())
                .withZipCode(entity.getZipCode())
                .withProvince(entity.getProvince())
                .withTypeOfAccount(entity.getTypeOfAccount().name().toLowerCase())
                .build();
    }

    @Override
    public Purchaser toEntity(PurchaserDto dto) {
        var result = new Purchaser();
        result.setId(dto.getId());
        result.setOfficialName(dto.getOfficialName());
        result.setCity(dto.getCity());
        result.setAddress(dto.getAddress());
        result.setZipCode(dto.getZipCode());
        result.setProvince(dto.getProvince());
        result.setTypeOfAccount(TypeOfAccount.valueOf(dto.getTypeOfAccount().toUpperCase()));

        return result;
    }
}
