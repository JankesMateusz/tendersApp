package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.Purchaser;
import com.jankes.tendersApp.purchasers.PurchaserDto;
import com.jankes.tendersApp.purchasers.PurchaserMapper;

public class TenderDto {

    private long id;
    private String mdpId;
    private String publicationDate;
    private String bidDate;
    private String siwzLink;
    private String bidNumber;
    private String title;
    private PurchaserDto purchaser;
//    private List<TenderItemDto> tenderItems;
    private String status;
    private String reportDate;
    private String budget;
    private String comments;

    public static Builder builder() {return new Builder();}

    public TenderDto(){}

    public TenderDto(Builder builder) {
        this.id = builder.id;
        this.mdpId = builder.mdpId;
        this.publicationDate = builder.publicationDate;
        this.bidDate = builder.bidDate;
        this.title = builder.title;
        this.siwzLink = builder.siwzLink;
        this.bidNumber = builder.bidNumber;
        this.purchaser = builder.purchaser;
//        this.tenderItems = builder.tenderItems;
        this.status = builder.status;
        this.reportDate = builder.reportDate;
        this.budget = builder.budget;
        this.comments = builder.comments;
    }

    public static class Builder {
        private long id;
        private String mdpId;
        private String publicationDate;
        private String bidDate;
        private String siwzLink;
        private String bidNumber;
        private String title;
        private PurchaserDto purchaser;
//        private List<TenderItemDto> tenderItems;
        private String status;
        private String reportDate;
        private String budget;
        private String comments;

        public Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withMdpId(String mdpId){
            this.mdpId = mdpId;
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

        public Builder withSiwzLink(String siwzLink){
            this.siwzLink = siwzLink;
            return this;
        }

        public Builder withBidNumber(String bidNumber){
            this.bidNumber = bidNumber;
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
//
//        public Builder withTenderItems(Set<TenderItem> tenderItems){
//            this.tenderItems = tenderItems.stream()
//                    .map(item -> new TenderItemMapper()
//                            .toDto(item))
//                    .collect(
//                            Collectors.toList());
//            return this;
//        }

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

        public Builder withComments(String remarks){
            this.comments = remarks;
            return this;
        }

        public TenderDto build() {return new TenderDto(this);}
    }

    public long getId() {
        return id;
    }

    public String getMdpId(){return mdpId;}

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getBidDate() {
        return bidDate;
    }

    public String getSiwzLink() {
        return siwzLink;
    }

    public String getBidNumber(){return bidNumber;}

    public String getTitle() {
        return title;
    }

    public PurchaserDto getPurchaser() {
        return purchaser;
    }
//
//    public List<TenderItemDto> getTenderItems() {
//        return tenderItems;
//    }

    public String getStatus() {
        return status;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getBudget() {
        return budget;
    }

    public String getComments(){
        return comments;
    }
}
