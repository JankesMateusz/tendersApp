package com.jankes.tendersApp.purchasers;

public class PurchaserDto {

    private final long id;
    private final String officialName;
    private final String city;
    private final String province;
    private final String address;
    private final String zipCode;
    private final String personOfContactFirstName;
    private final String typeOfAccount;
    private final String personOfContactLastName;
    private final String phone;
    private final String email;

    public static Builder builder() {
        return new Builder();
    }

    public PurchaserDto(Builder builder){
        this.id = builder.id;
        this.officialName = builder.officialName;
        this.city = builder.city;
        this.province = builder.province;
        this.typeOfAccount = builder.typeOfAccount;
        this.address = builder.address;
        this.zipCode = builder.zipCode;
        this.personOfContactFirstName = builder.personOfContactFirstName;
        this.personOfContactLastName = builder.personOfContactLastName;
        this.phone = builder.phone;
        this.email = builder.email;
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

    public String getPersonOfContactFirstName() {
        return personOfContactFirstName;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getPersonOfContactLastName() {
        return personOfContactLastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private long id;
        private String officialName;
        private String city;
        private String province;
        private String typeOfAccount;
        private String address;
        private String zipCode;
        private String personOfContactFirstName;
        private String personOfContactLastName;
        private String phone;
        private String email;

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

        public Builder withPersonOfContactFirstName(String personOfContactFirstName){
            this.personOfContactFirstName = personOfContactFirstName;
            return this;
        }

        public Builder withPersonOfContactLastName(String personOfContactLastName){
            this.personOfContactLastName = personOfContactLastName;
            return this;
        }

        public Builder withPhone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public PurchaserDto build(){
            return new PurchaserDto(this);
        }
    }
}
