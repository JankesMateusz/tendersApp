package com.jankes.tendersApp.contacts;

import com.jankes.tendersApp.common.DtoMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonInContactMapper implements DtoMapper<PersonInContactDto, PersonInContact> {

    @Override
    public PersonInContactDto toDto(PersonInContact entity) {
        return PersonInContactDto.builder()
                .withId(entity.getId())
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withEmail(entity.getEmail())
                .withEmail2(entity.getEmail2())
                .withPhone(entity.getPhone())
                .withFax(entity.getFax())
                .withPurchaser(entity.getPurchaser())
                .build();

    }

    @Override
    public PersonInContact toEntity(PersonInContactDto dto) {

        var result = new PersonInContact();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setEmail(dto.getEmail());
        result.setEmail2(dto.getEmail2());
        result.setPhone(dto.getPhone());
        result.setFax(dto.getFax());

        return result;
    }
}
