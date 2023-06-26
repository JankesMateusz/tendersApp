package com.jankes.tendersApp.contacts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/peopleInContact")
public class PersonInContactController {

    private final PersonInContactService service;

    public PersonInContactController(PersonInContactService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<PersonInContactDto> create(@RequestBody PersonInContactRequest toCreate){
        var result = service.createPersonInContact(toCreate.getPersonInContactDto(), toCreate.getPurchaserId());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

//    @GetMapping("/searchByPurchaser")
//    ResponseEntity<PersonInContactDto> findByPurchaser(@RequestParam(value = "id") Long id){
//
//    }
}
