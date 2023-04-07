package com.jankes.tendersApp.security;

import com.jankes.tendersApp.common.DtoMapper;
import com.jankes.tendersApp.tenders.TenderDto;

public class UserMapper implements DtoMapper<UserDto, User> {

    @Override
    public UserDto toDto(User entity){
        return UserDto.builder()
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole().toString())
                .build();
    }

    @Override
    public User toEntity(UserDto dto){
        var result = new User();
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setEmail(dto.getEmail());
        result.setPassword(dto.getPassword());
        result.setRole(Role.valueOf(dto.getRole()));

        return result;
    }


}
