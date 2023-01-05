package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.Purchaser;
import com.jankes.tendersApp.purchasers.PurchaserDto;
import com.jankes.tendersApp.purchasers.PurchaserMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TenderDto {

    private final long id;
    private final String publicationDate;
    private final String bidDate;
    private final String link;
    private final String title;
    private final PurchaserDto purchaser;
    private final List<TenderItemDto> tenderItems;
    private final String status;
    private final String reportDate;
    private final String budget;

    public static Builder builder() {return new Builder();}

    public TenderDto(Builder builder) {
        this.id = builder.id;
        this.publicationDate = builder.publicationDate;
        this.bidDate = builder.bidDate;
        this.title = builder.title;
        this.link = builder.link;
        this.purchaser = builder.purchaser;
        this.tenderItems = builder.tenderItems;
        this.status = builder.status;
        this.reportDate = builder.reportDate;
        this.budget = builder.budget;
    }

    public static class Builder {
        private long id;
        private String publicationDate;
        private String bidDate;
        private String link;
        private String title;
        private PurchaserDto purchaser;
        private List<TenderItemDto> tenderItems;
        private String status;
        private String reportDate;
        private String budget;

        public Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withPublicationDate(String publicationDate){
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withBidDate(String bidDate){
            this.bidDate = bidDate;
            return this;
        }

        public Builder withLink(String link){
            this.link = link;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withPurchaser(Purchaser purchaser){
            this.purchaser = new PurchaserMapper().toDto(purchaser);
            return this;
        }

        public Builder withTenderItems(Set<TenderItem> tenderItems){
            this.tenderItems = tenderItems.stream()
                    .map(item -> new TenderItemMapper()
                            .toDto(item))
                    .collect(
                            Collectors.toList());
            return this;
        }

        public Builder withStatus(String status){
            this.status = status;
            return this;
        }

        public Builder withReportDate(String reportDate){
            this.reportDate = reportDate;
            return this;
        }

        public Builder withBudget(String budget){
            this.budget = budget;
            return this;
        }

        public TenderDto build() {return new TenderDto(this);}
    }

    public long getId() {
        return id;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getBidDate() {
        return bidDate;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public PurchaserDto getPurchaser() {
        return purchaser;
    }

    public List<TenderItemDto> getTenderItems() {
        return tenderItems;
    }

    public String getStatus() {
        return status;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getBudget() {
        return budget;
    }
}
