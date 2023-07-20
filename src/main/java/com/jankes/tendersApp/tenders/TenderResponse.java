package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.contacts.PersonInContactDto;
import com.jankes.tendersApp.purchasers.PurchaserDto;

import java.util.List;

public class TenderResponse {

    TenderDto tenderDto;
    PurchaserDto purchaserDto;
    List<TenderItemDto> tenderItemsDto;
    PersonInContactDto personInContactDto;

    public TenderResponse(TenderDto tenderDto, PurchaserDto purchaserDto, List<TenderItemDto> tenderItemsDto, PersonInContactDto personInContactDto) {
        this.tenderDto = tenderDto;
        this.purchaserDto = purchaserDto;
        this.tenderItemsDto = tenderItemsDto;
        this.personInContactDto = personInContactDto;
    }

    public TenderDto getTenderDto() {
        return tenderDto;
    }


    public PurchaserDto getPurchaserDto() {
        return purchaserDto;
    }


    public List<TenderItemDto> getTenderItemsDto() {
        return tenderItemsDto;
    }

    public PersonInContactDto getPersonInContactDto(){return personInContactDto;}
}
