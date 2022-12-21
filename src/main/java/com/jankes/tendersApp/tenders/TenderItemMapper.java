package com.jankes.tendersApp.tenders;

interface TenderItemMapper {

    TenderItemDto toDto(TenderItem item);
    TenderItem toEntity(TenderItemDto dto);
}
