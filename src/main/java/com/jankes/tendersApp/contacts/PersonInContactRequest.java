package com.jankes.tendersApp.contacts;

public class PersonInContactRequest {

    private Long purchaserId;
    private PersonInContactDto personInContactDto;

    public PersonInContactRequest(Long purchaserId, PersonInContactDto personInContactDto) {
        this.purchaserId = purchaserId;
        this.personInContactDto = personInContactDto;
    }

    public Long getPurchaserId() {
        return purchaserId;
    }

    public PersonInContactDto getPersonInContactDto() {
        return personInContactDto;
    }
}
