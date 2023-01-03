package com.jankes.tendersApp.tenders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@RequestMapping("/tenders")
class TenderController {

    private final TenderService tenderService;

    TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping("/{id}")
    ResponseEntity<TenderDto> findTender(@PathVariable long id){
        TenderDto tender = tenderService.findSingleTender(id);
        if(tender == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tender, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<TenderDto>  create(@RequestBody TenderDto toCreate) {
        TenderDto  result = tenderService.saveTender(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<TenderDto> update(@PathVariable long id, @RequestBody TenderDto toUpdate) {
        if(id != toUpdate.getId() && toUpdate.getId() != 0){
            throw new IllegalStateException("Id in URL is not equal to Id in request: " + id + " != " + toUpdate.getId());
        }
        tenderService.saveTender(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
