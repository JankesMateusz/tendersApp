package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.common.DtoMapper;

import java.util.Optional;

public class PurchaserMapper implements DtoMapper<PurchaserDto, Purchaser> {

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
                .withTypeOfAccount(entity.getTypeOfAccount().name())
                .withEmail(entity.getEmail())
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
        result.setTypeOfAccount(TypeOfAccount.valueOf(dto.getTypeOfAccount()));
        result.setEmail(dto.getEmail());

        return result;
    }
}
