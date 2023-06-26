package com.jankes.tendersApp.contacts;

import com.jankes.tendersApp.purchasers.Purchaser;
import com.jankes.tendersApp.purchasers.PurchaserDto;
import com.jankes.tendersApp.purchasers.PurchaserMapper;

public class PersonInContactDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String email2;
    private String phone;
    private String fax;
    private PurchaserDto purchaser;

    public static Builder builder(){
        return new Builder();
    }

    public PersonInContactDto(){}

    public PersonInContactDto(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.email2 = builder.email2;
        this.phone = builder.phone;
        this.fax = builder.fax;
        this.purchaser = builder.purchaser;
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String email2;
        private String phone;
        private String fax;
        private PurchaserDto purchaser;

        public Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withEmail2(String email){
            this.email2 = email;
            return this;
        }

        public Builder withPhone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder withFax(String fax){
            this.fax = fax;
            return this;
        }

        public Builder withPurchaser(Purchaser purchaser){
            this.purchaser = new PurchaserMapper().toDto(purchaser);
            return this;
        }

        public PersonInContactDto build(){
            return new PersonInContactDto(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public PurchaserDto getPurchaser() {
        return purchaser;
    }
}
