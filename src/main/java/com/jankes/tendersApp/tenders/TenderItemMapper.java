package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.DtoMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TenderItemMapper implements DtoMapper<TenderItemDto, TenderItem> {

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
        var item = new TenderItem();
        item.setId(dto.getId());
        item.setCategory(ItemCategory.valueOf(dto.getCategory().toUpperCase()));
        item.setQuantity(dto.getQuantity());
        item.setOs(item.getOs());
        item.setOffice(item.getOffice());
        item.setRemarks(item.getRemarks());
        item.setTaskNumber(item.getTaskNumber());

        return item;
    }
}
