package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TenderDto {

    private long id;
    private Date publicationDate;
    private Date bidDate;
    private String link;

    private String title;
    private List<TenderItemDto> tenderItems;

    public TenderDto(Tender tender) {
        this.id = tender.getId();
        this.publicationDate = tender.getPublicationDate();
        this.bidDate = tender.getBidDate();
        this.link = tender.getLink();
        this.title = tender.getTitle();
        this.tenderItems = tender.getTenderItems()
                .stream()
                .map(item -> {
                    TenderItemMapper mapper = new TenderItemMapperImpl();
                    return mapper.toDto(item);
                })
                .collect(Collectors
                        .toList());
    }

    public TenderDto(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<TenderItemDto> getTenderItems() {
        return tenderItems;
    }

    public void setTenderItems(List<TenderItemDto> tenderItems) {
        this.tenderItems = tenderItems;
    }
}
