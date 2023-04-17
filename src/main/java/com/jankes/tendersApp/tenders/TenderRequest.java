package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;

import java.util.List;

public class TenderRequest {

    TenderDto tenderDto;
    List<TenderItemDto> tenderItems;
    PurchaserDto purchaserDto;

    public TenderDto getTenderDto() {
        return tenderDto;
    }

    public void setTenderDto(TenderDto tenderDto) {
        this.tenderDto = tenderDto;
    }

    public List<TenderItemDto> getTenderItems() {
        return tenderItems;
    }

    public void setTenderItems(List<TenderItemDto> tenderItems) {
        this.tenderItems = tenderItems;
    }

    public PurchaserDto getPurchaserDto() {
        return purchaserDto;
    }

    public void setPurchaserDto(PurchaserDto purchaserDto) {
        this.purchaserDto = purchaserDto;
    }
}
