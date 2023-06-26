package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.contacts.PersonInContact;
import com.jankes.tendersApp.tenders.Tender;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purchasers")
public class Purchaser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String officialName;
    private String city;
    private String province;
    private String address;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfAccount typeOfAccount;
    @OneToMany(mappedBy = "purchaser")
    private Set<Tender> tenders;
    @OneToMany(mappedBy = "purchaser")
    private Set<PersonInContact> peopleInContact;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public Set<Tender> getTenders() {
        return tenders;
    }

    public void setTenders(Set<Tender> tenders) {
        this.tenders = tenders;
    }

    public Set<PersonInContact> getPeopleInContact() {
        return peopleInContact;
    }

    public void setPeopleInContact(Set<PersonInContact> peopleInContact) {
        this.peopleInContact = peopleInContact;
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
