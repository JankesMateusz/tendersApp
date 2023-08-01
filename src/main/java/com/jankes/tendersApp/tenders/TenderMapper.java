package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.DtoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TenderMapper implements DtoMapper<TenderDto, Tender> {

    @Override
    public TenderDto toDto(Tender entity) {
        return TenderDto.builder()
                .withId(entity.getId())
                .withMdpId(entity.getMdpId())
                .withPublicationDate(format(entity.getPublicationDate()))
                .withBidDate(format(entity.getBidDate()))
                .withTitle(entity.getTitle())
                .withSiwzLink(entity.getSiwzLink())
                .withBidNumber(entity.getBidNumber())
                .withPurchaser(entity.getPurchaser())
                .withStatus(entity.getStatus().name())
                .withReportDate(format(entity.getReportDate()))
                .withEuFinancing(entity.isEuFinancing())
                .withBudget(entity.getBudget().name())
                .withComments(entity.getComments())
                .withLink1(entity.getLink1())
                .withLink2(entity.getLink2())
                .withLink3(entity.getLink3())
                .withPersonInContact(entity.getPersonInContact())
                .build();
    }

    @Override
    public Tender toEntity(TenderDto dto){
        var result = new Tender();
        result.setId(dto.getId());
        result.setMdpId(dto.getMdpId());
        result.setPublicationDate(LocalDate.parse(dto.getPublicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        result.setBidDate(LocalDate.parse(dto.getBidDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        result.setTitle(dto.getTitle());
        result.setSiwzLink(dto.getSiwzLink());
        result.setBidNumber(dto.getBidNumber());
        result.setStatus(Tender.Status.valueOf(dto.getStatus()));
        result.setReportDate(LocalDate.parse(dto.getReportDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        result.setEuFinancing(dto.isEuFinancing());
        result.setBudget(TenderBudget.valueOf(dto.getBudget()));
        result.setComments(dto.getComments());
        result.setLink1(dto.getLink1());
        result.setLink2(dto.getLink2());
        result.setLink3(dto.getLink3());

        return result;
    }

    private String format(LocalDate time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(formatter);
    }
}
