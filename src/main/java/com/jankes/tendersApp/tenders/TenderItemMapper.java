package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.DtoMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class TenderItemMapper implements DtoMapper<TenderItemDto, TenderItem> {

    @Override
    public TenderItemDto toDto(TenderItem item) {
        return TenderItemDto.builder()
                .withId(Optional.ofNullable(item.getId()).orElse(0L))
                .withCategory(item.getCategory().name())
                .withQuantity(Optional.ofNullable(item.getQuantity()).orElse(0))
                .withCpuQuantity(Optional.ofNullable(item.getCpuQuantity()).orElse(0))
                .withOs(item.getOs())
                .withOffice(item.getOffice())
                .withRemarks(item.getRemarks())
                .withTaskNumber(Optional.ofNullable(item.getTaskNumber()).orElse(0))
                .withInitialBenchmark(item.getInitialBenchmark())
                .withISO50001(item.isISO50001())
                .withTCO(item.isTCO())
                .withPurchaseForm(item.getPurchaseForm())
                .withWinner(item.getWinner())
                .withVendor(item.getVendor())
                .withStatus(item.getStatus())
                .withAwardLetterDate(format(item.getAwardLetterDate()))
                .withComments(item.getComments())
                .withFinalOS(item.getFinalOS())
                .withFinalOffice(item.getFinalOffice())
                .withDeliveryTerm(item.getDeliveryTerm())
                .withBenchmarkBid(item.getBenchmarkBid())
                .build();
    }

    @Override
    public TenderItem toEntity(TenderItemDto dto) {
        var item = new TenderItem();
        item.setId(dto.getId());
        item.setCategory(ItemCategory.valueOf(dto.getCategory().toUpperCase()));
        item.setQuantity(dto.getQuantity());
        item.setCpuQuantity(dto.getCpuQuantity());
        item.setOs(dto.getOs());
        item.setOffice(dto.getOffice());
        item.setRemarks(dto.getRemarks());
        item.setTaskNumber(dto.getTaskNumber());
        item.setPurchaseForm(dto.getPurchaseForm());
        item.setInitialBenchmark(dto.getInitialBenchmark());
        item.setISO50001(dto.isIso50001());
        item.setTCO(dto.isTco());
        item.setWinner(dto.getWinner());
        item.setVendor(dto.getVendor());
        item.setStatus(dto.getStatus());
        item.setAwardLetterDate(LocalDate.parse(dto.getAwardLetterDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        item.setComments(dto.getComments());
        item.setFinalOS(dto.getFinalOS());
        item.setFinalOffice(dto.getFinalOffice());
        item.setDeliveryTerm(dto.getDeliveryTerm());
        item.setBenchmarkBid(dto.getBenchmarkBid());

        return item;
    }

    private String format(LocalDate time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(formatter);
    }
}
