package com.jankes.tendersApp.purchasers;

public class PurchaserDto {

    private long id;
    private String officialName;
    private String city;
    private String province;
    private String address;
    private String zipCode;
    private String typeOfAccount;


    public static Builder builder() {
        return new Builder();
    }

    public PurchaserDto(){}

    public PurchaserDto(Builder builder){
        this.id = builder.id;
        this.officialName = builder.officialName;
        this.city = builder.city;
        this.province = builder.province;
        this.typeOfAccount = builder.typeOfAccount;
        this.address = builder.address;
        this.zipCode = builder.zipCode;
    }

    public Long getId() {
        return id;
    }

    public String getOfficialName() {
        return officialName;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public static class Builder {
        private long id;
        private String officialName;
        private String city;
        private String province;
        private String typeOfAccount;
        private String address;
        private String zipCode;

        public Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withOfficialName(String officialName){
            this.officialName = officialName;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Builder withProvince(String province){
            this.province = province;
            return this;
        }

        public Builder withTypeOfAccount(String typeOfAccount){
            this.typeOfAccount = typeOfAccount;
            return this;
        }

        public Builder withAddress(String address){
            this.address = address;
            return this;
        }

        public Builder withZipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }
        public PurchaserDto build(){
            return new PurchaserDto(this);
        }
    }
}
