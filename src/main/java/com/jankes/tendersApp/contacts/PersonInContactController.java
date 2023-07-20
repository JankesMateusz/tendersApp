package com.jankes.tendersApp.contacts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/searchByPurchaser")
    ResponseEntity<List<PersonInContactDto>> findByPurchaser(@RequestParam(value = "id") Long id){
        var result = service.findAllForPurchaser(id);
        if (result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
