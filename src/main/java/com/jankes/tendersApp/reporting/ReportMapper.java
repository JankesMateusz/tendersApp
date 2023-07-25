package com.jankes.tendersApp.reporting;

import com.jankes.tendersApp.common.DtoMapper;
import com.jankes.tendersApp.tenders.Tender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Service
public class ReportMapper implements DtoMapper<ReportDto, Tender> {

    @Override
    public ReportDto toDto(Tender entity) {
        return ReportDto.builder()
                .withPurchaserName(entity.getPurchaser().getOfficialName())
                .withCity(entity.getPurchaser().getCity())
                .withAddress(entity.getPurchaser().getAddress())
                .withZipCode(entity.getPurchaser().getZipCode())
                .withProvince(entity.getPurchaser().getProvince())
                .withTypeOfAccount(entity.getPurchaser().getTypeOfAccount().name())
                .withPublicationDate(format(entity.getPublicationDate()))
                .withBidDate(format(entity.getBidDate()))
                .withTitle(entity.getTitle())
                .withLink(entity.getSiwzLink())
                .withBidNumber(entity.getBidNumber())
                .withReportDate(format(entity.getReportDate()))
                .withStatus(entity.getStatus().name())
                .withBudget(entity.getBudget().name())
                .withRemarks(entity.getComments())
                .withTenderItems(entity.getTenderItemsDto())
                .build();
    }

    @Override
    public Tender toEntity(ReportDto dto) {
        return null;
    }

    private String format(LocalDate time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return time.format(formatter);
    }
}
