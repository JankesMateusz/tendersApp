package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.DtoMapper;
import com.jankes.tendersApp.purchasers.PurchaserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class TenderMapper implements DtoMapper<TenderDto, Tender> {

    @Override
    public TenderDto toDto(Tender entity) {
        return TenderDto.builder()
                .withId(entity.getId())
                .withPublicationDate(format(entity.getPublicationDate()))
                .withBidDate(format(entity.getBidDate()))
                .withTitle(entity.getTitle())
                .withLink(entity.getLink())
                .withPurchaser(entity.getPurchaser())
                .withTenderItems(entity.getTenderItems())
                .build();
    }

    @Override
    public Tender toEntity(TenderDto dto){
        var result = new Tender();
        result.setId(dto.getId());
        result.setPublicationDate(LocalDate.parse(dto.getPublicationDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        result.setBidDate(LocalDate.parse(dto.getBidDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        result.setTitle(dto.getTitle());
        result.setLink(dto.getLink());
        result.setPurchaser(new PurchaserMapper().toEntity(dto.getPurchaser()));
        result.setTenderItems(
                dto.getTenderItems()
                        .stream()
                        .map(item -> new TenderItemMapper().toEntity(item))
                        .collect(Collectors.toSet()));
        return result;
    }

    private String format(LocalDate time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return time.format(formatter);
    }
}
