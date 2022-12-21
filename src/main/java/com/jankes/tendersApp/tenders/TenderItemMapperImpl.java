package com.jankes.tendersApp.tenders;

import java.util.Optional;

class TenderItemMapperImpl implements TenderItemMapper{

    @Override
    public TenderItemDto toDto(TenderItem item) {
        return TenderItemDto.builder()
                .withId(Optional.ofNullable(item.getId()).orElse(0L))
                .withCategory(item.getCategory().name().toLowerCase())
                .withQuantity(Optional.ofNullable(item.getQuantity()).orElse(0))
                .withCpuQuantity(Optional.ofNullable(item.getCpuQuantity()).orElse(0))
                .withOs(item.getOs())
                .withOffice(item.getOffice())
                .withRemarks(item.getRemarks())
                .withTaskNumber(Optional.ofNullable(item.getTaskNumber()).orElse(0))
                .build();
    }

    @Override
    public TenderItem toEntity(TenderItemDto dto) {
        return null;
    }
}
