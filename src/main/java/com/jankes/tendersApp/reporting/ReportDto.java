package com.jankes.tendersApp.reporting;

import com.jankes.tendersApp.tenders.TenderItemDto;

import java.util.List;

public class ReportDto {

    private final String purchaserName;
    private final String city;
    private final String province;
    private final String typeOfAccount;
    private final String address;
    private final String zipCode;
    private final String personOfContactFirstName;
    private final String personOfContactLastName;
    private final String email;
    private final String phoneNumber;
    private final String title;
    private final String publicationDate;
    private final String bidDate;
    private final String bidNumber;
    private final String link;
    private final String status;
    private final String reportDate;
    private final String budget;
    private final List<TenderItemDto> tenderItems;
    private final String remarks;

    public static Builder builder() {
        return new Builder();
    }

    public ReportDto(Builder builder) {
        this.purchaserName = builder.purchaserName;
        this.city = builder.city;
        this.province = builder.province;
        this.typeOfAccount = builder.typeOfAccount;
        this.address = builder.address;
        this.zipCode = builder.zipCode;
        this.personOfContactFirstName = builder.personOfContactFirstName;
        this.personOfContactLastName = builder.personOfContactLastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.title = builder.title;
        this.publicationDate = builder.publicationDate;
        this.bidDate = builder.bidDate;
        this.bidNumber = builder.bidNumber;
        this.link = builder.link;
        this.status = builder.status;
        this.reportDate = builder.reportDate;
        this.budget = builder.budget;
        this.tenderItems = builder.tenderItems;
        this.remarks = builder.remarks;
    }

    public static class Builder {
        private String purchaserName;
        private String city;
        private String province;
        private String typeOfAccount;
        private String address;
        private String zipCode;
        private String personOfContactFirstName;
        private String personOfContactLastName;
        private String email;
        private String phoneNumber;
        private String title;
        private String publicationDate;
        private String bidDate;
        private String bidNumber;
        private String link;
        private String status;
        private String reportDate;
        private String budget;
        private List<TenderItemDto> tenderItems;
        private String remarks;

        public Builder withPurchaserName(String purchaserName) {
            this.purchaserName = purchaserName;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder withTypeOfAccount(String typeOfAccount) {
            this.typeOfAccount = typeOfAccount;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder withPersonOfContactFirstName(String personOfContactFirstName) {
            this.personOfContactFirstName = personOfContactFirstName;
            return this;
        }

        public Builder withPersonOfContactLastName(String personOfContactLastName) {
            this.personOfContactLastName = personOfContactLastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withPublicationDate(String publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withBidDate(String bidDate) {
            this.bidDate = bidDate;
            return this;
        }

        public Builder withBidNumber(String bidNumber){
            this.bidNumber = bidNumber;
            return this;
        }

        public Builder withLink(String link) {
            this.link = link;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withReportDate(String reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public Builder withBudget(String budget) {
            this.budget = budget;
            return this;
        }

        public Builder withTenderItems(List<TenderItemDto> tenderItems) {
            this.tenderItems = tenderItems;
            return this;
        }

        public Builder withRemarks(String remarks){
            this.remarks = remarks;
            return this;
        }

        public ReportDto build() {
            return new ReportDto(this);
        }
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPersonOfContactFirstName() {
        return personOfContactFirstName;
    }

    public String getPersonOfContactLastName() {
        return personOfContactLastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getBidDate() {
        return bidDate;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public String getLink() {
        return link;
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

    public List<TenderItemDto> getTenderItems() {
        return tenderItems;
    }

    public String getRemarks() {
        return remarks;
    }
}
