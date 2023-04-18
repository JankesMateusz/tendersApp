package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;

import java.util.List;

public class TenderRequest {

    Long purchaserId;
    TenderDto tenderDto;
    List<TenderItemDto> tenderItems;

    public Long getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Long id) {
        this.purchaserId = id;
    }

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

}
