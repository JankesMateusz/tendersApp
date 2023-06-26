package com.jankes.tendersApp.contacts;

import com.jankes.tendersApp.purchasers.Purchaser;
import com.jankes.tendersApp.tenders.Tender;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PersonInContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String email2;
    private String phone;
    private String fax;
    @OneToMany(mappedBy = "personInContact")
    private Set<Tender> tenders;
    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private Purchaser purchaser;

    PersonInContact(){};

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getEmail2() {
        return email2;
    }

    void setEmail2(String email2) {
        this.email2 = email2;
    }

    String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    String getFax() {
        return fax;
    }

    void setFax(String fax) {
        this.fax = fax;
    }

    public Set<Tender> getTenders() {
        return tenders;
    }

    public void setTenders(Set<Tender> tenders) {
        this.tenders = tenders;
    }

    Purchaser getPurchaser() {
        return purchaser;
    }

    void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }
}
