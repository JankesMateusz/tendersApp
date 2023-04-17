package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.tenders.Tender;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purchasers")
public class Purchaser {

    @Id
    @GeneratedValue
    private Long id;
    private String officialName;
    private String city;
    private String province;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfAccount typeOfAccount;
    private String address;
    private String zipCode;
    private String personOfContactFirstName;
    private String personOfContactLastName;
    private String email; //TODO validation
    private String phoneNumber; //TODO validation?
    @OneToMany(mappedBy = "purchaser")
    Set<Tender> tenders;

    public Purchaser(){}

    public void addTender(Tender tender){
        tenders.add(tender);
        tender.setPurchaser(this);
    }

    public void removeTender(Tender tender){
        if(!tenders.contains(tender)){
            return;
        }
        tenders.remove(tender);
        tender.setPurchaser(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Purchaser other))
            return false;

        return this.getId() != null &&
                this.getId().equals(other.getId());
    }
}
