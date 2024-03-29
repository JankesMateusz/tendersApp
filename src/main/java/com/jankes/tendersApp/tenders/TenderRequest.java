package com.jankes.tendersApp.tenders;

import java.util.List;

public class TenderRequest {

    Long purchaserId;
    TenderDto tenderDto;
    List<TenderItemDto> tenderItems;
    Long personInContactId;

    public Long getPurchaserId() {
        return purchaserId;
    }


    public TenderDto getTenderDto() {
        return tenderDto;
    }


    public List<TenderItemDto> getTenderItems() {
        return tenderItems;
    }

    public Long getPersonInContactId(){return personInContactId;}

}
