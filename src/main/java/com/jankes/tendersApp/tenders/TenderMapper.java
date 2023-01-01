package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.DtoMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenderMapper implements DtoMapper<TenderDto, Tender> {

    @Override
    public TenderDto toDto(Tender entity) {
        return TenderDto.builder()
                .withId(entity.getId())
                .withPublicationDate(
                        new SimpleDateFormat("dd-MM-yyyy")
                        .format(entity.getPublicationDate()))
                .withBidDate(new SimpleDateFormat("dd-MM-yyyy")
                        .format(entity.getBidDate()))
                .withTitle(entity.getTitle())
                .withLink(entity.getLink())
                .withTenderItems(entity.getTenderItems())
                .build();
    }

    @Override
    public Tender toEntity(TenderDto dto) throws Exception {
        var result = new Tender();
        result.setId(dto.getId());
        result.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse(dto.getPublicationDate()));
        result.setBidDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse(dto.getBidDate()));
        result.setTitle(dto.getTitle());
        result.setLink(dto.getLink());
        result.setTenderItems(
                dto.getTenderItems()
                        .stream()
                        .map(item -> new TenderItemMapper().toEntity(item))
                        .collect(Collectors.toSet()));
        return result;
    }
}
