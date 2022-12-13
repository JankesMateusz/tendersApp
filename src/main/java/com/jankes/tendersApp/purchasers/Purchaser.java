package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.common.BaseEntity;
import com.jankes.tendersApp.tenders.Tender;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Purchaser extends BaseEntity {

    private String officialName;
    private String city;
    private String province;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfAccount typeOfAccount;
    private String address;
    private String zipCode;
    @Embedded
    private PersonOfContact personOfContact;
    private String email; //TODO validation
    private String phoneNumber; //TODO validation
    @OneToMany(mappedBy = "purchaser")
    Set<Tender> tenders;

    public Purchaser(){}

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

    public PersonOfContact getPersonOfContact() {
        return personOfContact;
    }

    public void setPersonOfContact(PersonOfContact personOfContact) {
        this.personOfContact = personOfContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Tender> getTenders() {
        return tenders;
    }

    public void setTenders(Set<Tender> tenders) {
        this.tenders = tenders;
    }
}
