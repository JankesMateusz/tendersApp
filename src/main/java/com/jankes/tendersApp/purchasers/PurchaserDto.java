package com.jankes.tendersApp.purchasers;

public class PurchaserDto {

    private Long id;
    private String officialName;
    private String city;
    private String province;
    private TypeOfAccount typeOfAccount;
    private String address;
    private String zipCode;
    private String personOfContactFirstName;
    private String personOfContactLastName;
    private String phone;
    private String email;


    public PurchaserDto(Purchaser purchaser){
        this.id = purchaser.getId();
        this.officialName = purchaser.getOfficialName();
        this.city = purchaser.getCity();
        this.province = purchaser.getProvince();
        this.typeOfAccount = purchaser.getTypeOfAccount();
        this.address = purchaser.getAddress();
        this.zipCode = purchaser.getZipCode();
        this.personOfContactFirstName = purchaser.getPersonOfContact().getFirstName();
        this.personOfContactLastName = purchaser.getPersonOfContact().getLastName();
        this.phone = purchaser.getPhoneNumber();
        this.email = purchaser.getEmail();
    }

    public Long getId(){
        return id;
    }
    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPersonOfContactFirstName() {
        return personOfContactFirstName;
    }

    public void setPersonOfContactFirstName(String personOfContactFirstName) {
        this.personOfContactFirstName = personOfContactFirstName;
    }

    public String getPersonOfContactLastName() {
        return personOfContactLastName;
    }

    public void setPersonOfContactLastName(String personOfContactLastName) {
        this.personOfContactLastName = personOfContactLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
