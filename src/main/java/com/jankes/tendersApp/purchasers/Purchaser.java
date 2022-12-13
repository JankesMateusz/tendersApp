package com.jankes.tendersApp.purchasers;

import com.jankes.tendersApp.common.BaseEntity;

import javax.persistence.*;

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
    //@OneToMany(mappedBy = "purchaser")
    //Set<Tender> tenders;


}
