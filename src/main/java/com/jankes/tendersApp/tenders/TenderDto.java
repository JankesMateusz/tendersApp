package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TenderDto {

    private final long id;
    private final PurchaserDto purchaser;
    private final Date publicationDate;
    private final Date bidDate;
    private final String link;
    private final List<TenderItemDto> tenderItems;

    public TenderDto(Tender tender) {
        this.id = tender.getId();
        this.purchaser = new PurchaserDto(tender.getPurchaser());
        this.publicationDate = tender.getPublicationDate();
        this.bidDate = tender.getBidDate();
        this.link = tender.getLink();
        this.tenderItems = tender.getTenderItems()
                .stream()
                .map(TenderItemDto::new)
                .collect(Collectors
                        .toList());
    }

    public long getId() {
        return id;
    }

    public PurchaserDto getPurchaser(){
        return purchaser;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public String getLink() {
        return link;
    }

    public List<TenderItemDto> getTenderItems() {
        return tenderItems;
    }
}
