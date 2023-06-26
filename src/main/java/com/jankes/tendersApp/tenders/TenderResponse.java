package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;

import java.util.List;

public class TenderResponse {

    TenderDto tenderDto;
    PurchaserDto purchaserDto;
    List<TenderItemDto> tenderItemsDto;

    public TenderResponse(TenderDto tenderDto, PurchaserDto purchaserDto, List<TenderItemDto> tenderItemsDto) {
        this.tenderDto = tenderDto;
        this.purchaserDto = purchaserDto;
        this.tenderItemsDto = tenderItemsDto;
    }

    public TenderDto getTenderDto() {
        return tenderDto;
    }

    public void setTenderDto(TenderDto tenderDto) {
        this.tenderDto = tenderDto;
    }

    public PurchaserDto getPurchaserDto() {
        return purchaserDto;
    }

    public void setPurchaserDto(PurchaserDto purchaserDto) {
        this.purchaserDto = purchaserDto;
    }

    public List<TenderItemDto> getTenderItemsDto() {
        return tenderItemsDto;
    }

    public void setTenderItemsDto(List<TenderItemDto> tenderItemsDto) {
        this.tenderItemsDto = tenderItemsDto;
    }
}
