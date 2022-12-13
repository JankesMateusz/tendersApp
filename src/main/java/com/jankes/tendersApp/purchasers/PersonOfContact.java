package com.jankes.tendersApp.purchasers;

import javax.persistence.Embeddable;

@Embeddable
public class PersonOfContact {

    private String firstName;
    private String lastName;

    public PersonOfContact(){}


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
